
[1]: https://github.com/lsund/suffix-trees-haskell/blob/master/src/LazyTree.hs
[2]: https://github.com/lsund/suffix-trees-haskell/blob/master/src/Ukkonen.hs
[3]: https://github.com/lsund/suffix-trees-haskell/blob/master/src/McCreight.hs

# Functional Suffix Tree Algorithms

**OBSERVE! I did not invent the following content! This is a summary of Robert
Gieberich and Stefan Kurtz 1995 article *A comparison of imperative and purely
functional suffix tree constructions* published in the Science of Computer
Programming 25 p. 187-218. All credits to the original authors.**

**OBSERVE! Even though the following text referst to 'the article', This is not
a scientific resource. This is aimed to be an informal summary of the article
mentioned abeve. See the original article for the real content.**

## Introduction

This article offers a simpler and more descriptive explanation of the famous
algorithms of Ukkonen and McCreight. Furthermore, a new, lazy suffix tree
construction algorithm is presented that is even simpler. Both imperative and
functional implementations are considered.

Suffix trees have many applications. Some of those are:

- Finding repetitions and palindromes
- Deriving q-gram profiles
- Matching statistics (for fast approximate matching)
- Searching text in reverse
- Searching genetic complement

Suffix trees is a very valuable method when one text needs to be searched for
multiple substrings. Suffix trees has a long history, starting with Weiner in
1970. Later authors have developed easier to understand solutions, sometimes
application specific. In 1995, Ukkonen designed a general suffix tree
algorithm 1995 processes the string from left to right, has a simple
description and is an online algorithm. What more can one ask for?

## The new approach

By using a functional approach for string algorithms, we can describe our
algorithms in a more declarative way. In addition, functional languages allows
for an easy description of a polymorphic alphabet (meaning the suffix trees can
represent strings of elements of arbitrary type). It is of course also nice if
the description is clear and easy to understand, which functional programming
allows for. In addition, suffix trees can benefit from the lazy aspect of
languages such as `haskell` or `miranda`. Being lazy means that when traversing
the suffix tree, parts that are not needed can be left unevaluated.

Suffix tree constructions so far only done in imperative style. Therefore, this
paper is about describing suffix tree algorithms in a functional style. The
algorithms include Ukkonen, McCreight and a new algorithm called `lazyTree`.

## The Suffix Tree Family

We introduce the so called A+ trees then suffix trees and then some further
specifications of suffix trees. The different version of suffix trees here are
called atomic, position and compact suffix trees, and they differ in terms of
the number of nodes. In theory, the compact tree (which has the least number of
nodes is the optimal tree) is optimal, but in practice, other versions might be
better.

### A+ Trees

Suppose the set of possible characters (the alphabet) is denoted by `A` and
that the trees are built from a string (also called a text) denoted by `x`.

Then an A+ tree is a rooted trie. The edge labels of the tree are non-empty
sequences from A called strings. Since the edges of a branch are unique, paths
from root along the tree are also unique.

### Suffix Trees

A Suffix Tree is a A+ tree such that for every substring of `x`, there is a
path in the tree and vice versa. The name suffix tree comes from the
observation that unique suffixes correspond to root-leaf paths in the tree.

To make all suffixes unique, one can add a so called sentinel character to the
end of the string. The sentinel character just needs to be a character that
is not contained in the alphabet.

#### Different kinds of suffix trees.

The compact suffix tree of `x, cst(x)` has a minimal number of nodes `O(n)`,
the atomic suffix tree of `x, ast(x)` has a maximal number of nodes `O(n^2)`
and the position tree somewhere in between. `pst(x)` emerges from `ast(x)` by
concatenating edges as long as they are not leading to a leaf node.

## Functional suffix tree algorithms

What follows is simple ways of constructing suffix trees using functional
concepts.

### Functional lazyTree

There are two simple intuitive approaches to suffix tree constuction. One is by
successively inserting the suffixes of into an empty tree. This is the basis
for Weiner's, McCreight's and Ukkonen's method, by iterating over the input
text. It is an imperative idea by nature as it uses successive tree
modifications and global access.

The alternative approach focuses on the data structure, the suffix tree. It
first determines the outgoing edges of the root and then constructs their
subtrees recursively in a top-down manner. No tree modifications are necessary.
This approach has not been studied before, since the imperative approach seemed
to leave no room for improvement. We will see that this is not entirely true.

Here, we call a construction algorithms lazy if it construction the entire
suffix tree from the root towards the leaves. This has the advantage that tree
construction may be interleaved with tree traversal - paths that was yet not
traversed does not need to be created directly. This is achieved for free when
using a lazy language such as `Haskell` or `Miranda`.

Here follows a high level description of the new algorithm `lazyTree`. Spawn
the root node and a sorted list of all unique suffixes of `x`. Now group this
list according to the first character and then remove the first character of
each element. Then `pst(x)` emerges by (for an non-empty group) creating an
edge with the single character of the group and then recursing for the tails of
the group. The recursion terminates with a leaf edge when the group only has
one element.

Actually, using a sorted list only helps on paper. All the algorithm needs is
to be able to group the suffixes as above and then choose a common prefix of
each group. If you instead of just the single character choose the longest
common prefix, `cst(x)` emerges.

We create data types for the suffix tree and edge functions. We make aliases
for a list of suffixes and a label. The aliases and types are polymorphic over
the type variable `a`.

An `EdgeFunction` takes a list of suffixes and splits off a common prefix. If
different `EdgeFunction`'s are supplied, the different trees are constructed.
`edgeAST` just returns, since the first letter already has been split of.
`edgePST` proceeds similarly but takes the whole label as suffix if there only
exists one element in the suffix list. `edgeCST` does some extra work and
always extracts the longest common prefix as label.

The implementation can be found [here][1].

### Functional Ukkonen

What follows is a simplified, functional version of Ukkonen's algorithm called
`naiveOnline`. While Ukkonen derives his construction in an imperative style
using an atomic suffix tree as intermediate step, this is a more direct
presentation, which leads to a more transparent construction.

Online construction means generating a series of suffix trees for longer and
longer prefixes of the text. While the suffix tree for the empty tree is
trivial, the step `cst(x)` to `cst(xa)` is not trivial. This is the crucial
part of the algorithm.

We know that `cst(x)` represents all substrings of `x`. `cst(xa)` must thus
represent all substrings of `xa`. Therefore, we consider all substrings of `xa`
that does not occur in `cst(x)`.

If `cst(x)` has a leaf with edge label `s`, then `cst(xa)` will have a leaf
with edge label `sa`. This observation led Ukkonen to represent leaves as open,
ie. only only with one index. It is implicit that the leaves index end at
length of the current prefix.

The suffix tree produced by the online algorithm can be represented by the
global text object `x` and its length and the tree structure itself with
indexed edge labels.

Notes:

1. The edge label might occur many times in `x`, then the indices are arbitrary
   between these choices.

2. The global value length grows with the text, and so does the end index of
   leaf edges. Nothing has to be done when inserting a suffix `sa` if `sa` is a
   leaf.

Because of the above, we only consider the suffixes that are (1) not in
`cst(x)` and (2) not leaves. We call these *relevant* suffixes. If we insert
the relevant suffixes into `cst(x)`, we get `cst(xa)`.

The relevant suffixes are the list of the longest nested suffixes of `x`.
These are called active suffixes. Also, the active suffixes of `x` denoted
`alpha(x)` are "between" `alpha(x)a` and `alpha(ta)`.

With this knowledge, the new description becomes:

Take the suffixes of `alpha(x)a` one after the other by decreasing length and
insert them into `cst(x)` until a suffix is found which is a substring of `x`.

Assume you want to insert a suffix `s` into the tree `E`. We then need to find
the node that splits `s` into the longest prefix. We'll call this node `s` and
we'll call `(b, u)` the reference pair where `b` is the string before the node
and `u` is the string after the node in the tree.

With this in consideration, the final description of the algorithm is:

```
update(E, sa)
    | substring sa x = (E, sa)
    | isEmpty sa     = (E `union` (s', i), empty)
    | otherwise     = update (E `union (s, i)) (drop 1 sa)
```

`drop` is the function that returns the string with the first `n` characters
removed. There are two critical operations in this algorithm. Checking whether
`sa` is a substring of `x` and splitting some edge to introduce `s'`, if
necessary. Both these operations are easy if we computed the reference pair `s'
= (b, u)`.

The easiest way to determine `s'` is to follow the path for `s` down from the
root. This leads to a non-linear construction, as the length of this path can
be `O(n)` in the worst case and there are `O(n)` suffixes.

An implementation of this description can be found [here][2].

### Functional McCreight

This is a simplified version of McCreight's algorithm called `naiveInsertion`.
Here, we consider a text of length larger than 2, which contains the sentinel
character as defined above.

Let `head(s)` denote the longest repeated suffix of `s`, if `x != s`. The
remainder in a string after `head` we call `tail`.

The general structure of McCreight's algorithm is to compute `cst(x)` by
successively inserting the suffixes `s` of `x` into the tree. Thus, the
intermediate trees are not suffix trees, only `t1` and `tn` are. The algorithm
computes the reference pair denoted by `(h,q)` where `h` is the head. The
easiest way to do this is to follow the path for `s` in `T(as)` down from the
root until one "falls out of the tree". This can be described by the function
`scan`.

```
scan(E,b,i)
    | b has no xi-edge in E = ((b, empty), i)
    | |p| < r - l + 1       = ((b, p)    , i + |p|)
    | otherwise             = scan(E, v, i + |P|)
```

Where `p` is the longest common prefix of `t1..tr` and `ti..tn`. We use `scan`
to find the position where we should insert a suffix of `x` that starts at
position `i`.

An implementation of this description can be found [here][3].


### Efficiency of the algorithms

The function `length` is in the algorithms to determine the length of the
edge label. We consider this a constant operation for the following reasons:

* In principle, it is possible to avoid calling `length` by initially
  associating all suffixes with their lengths, and decreasing the lengths as
  the suffixes are shortened. This however, leads to a more difficult to read
  program.
* If `x` is represented as an array instead of a list( which it perfectly well
  could), the operation becomes constant.
* Even with the given program, the length is not calculated until the edge is
  traversed (due to laziness).

Let the length of the text be `n`, and the size of the alphabet be `k`. The
analysis of `naiveOnline` and `naiveInsertion` is as follows:

There are `O(n)` nodes created. The length to access each node is worst case
`O(n)` and `O(logn)` average. Selecting the branch at each node introduces a
factor `k`. In summary, worst case `O(kn^2)` and average `O(knlogn)`.

The alphabet factor has a strong influence, since nodes close to the root will
have close to `k` branches. This is partly compensated by the tree becoming
flatter for large alphabets.

The analysis of `lazyTree` is as follows: The runtime of this algorithm is
determined by considering the number of letters read from all suffixes, and the
number of operations per letter read. The sum of the suffix lengths is `O(n^2)`
(`n(n + 1) / 2`). All suffixes except for the longest are read to the last
letter. Since `lazyTree` uses iteration over the alphabet to group suffixes
according to their first letter, each letter is inspected `k` times. This
yields a worst case bound of `O(kn^2)`.

The expected length of the longest repeated substring is `O(logn)`. Since no
suffix is read after it has become unique, we get an average case bound of
`O(knlogn)`.

Note that while `lazyTree`'s factor of `k` stems from the iteration over the
alphabet for grouping suffixes, for `naiveInsertion` and `naiveOnline` this
factor comes from the checking of `k` edges on each branch.

A note on traversing the tree: While traversing, we only need to compare the
first letter of the edge label to the first letter of the current suffix. The
subsequent letters of the edge label must match the suffix, since the suffix is
repeated and therefore occurs in `cst(x)`. With this in consideration, an edge
can be traversed in constant time.

#### Some empirical results

The following are some experiments for some random texts with alphabet sizes 4,
20, 50 and 90. Running on a SPARCstation 10/41 with 32 MB memory.

Some properties:

* All algorithms show close to linear behavior.

* Independent of the alphabet size, `lazyTree` is the fastest of the three
  algorithms, with the advantage decreasing for large alphabets. This effect
  lies with constant factors. For lazyTree, the factor is truly `k`, while for
  `naiveInsertion` and `naiveOnline`, the expected factor is `k/2`. This
  becomes visible with increasing `k`.

* `naiveInsertion` is always somewhat faster than `naiveOnline`. The difference
  is reading edge labels as explained above, which does not pay of for
  `naiveOnline` because the expected length of inner edge labels is close to 1.
