# Category Theory 1

*8 Minute read*

This is going to be a series of blog posts on Category Theory. The aim is to
make it short, sweet and to the point. Obviously, it won't be a place for deep
understanding and complex ideas. At best, it'll scratch the surface of Category
Theory - arguably the most interesting branch of mathematics from a programmers
point of view. The following posts are largely based on the excellent content
by Bartosz Milewski. If you are interested in Category Theory, I'd suggest
you'd have a look at his [youtube channel][2] or [blog][3].

## Introduction

What would happen if you decided to create a language that abstracts over
already extremely abstract mathematical concepts such as [sets][6], [rings][7]
or [groups][8]? What possible advantage would this have, and what properties
could such a language describe?

By removing about all kinds of structure in problem solving, Category Theory
only describes relations between things. Before diving into its real purpose
and properties however, let us try to relate it to the art of programming.
Programming turns out to be one application area of Category Theory, in
particular for discover powerful and elegant abstractions.

## Category Theory and Programming Languages

The programming language laying closest to the machine is assembly language,
which more or less is a direct mapping between statements and CPU instructions.
Writing programs in assembly language is as we all know, very intricate and far
for scalable. Thus, with the appearance of the first computers, computer
science spawned a branch called, called 'Programming language theory',
dedicated to designing languages understandable by both man and machine.

The interest has of course always been designing a language, as convenient
humans as possible, but still efficient and understandable for the computer.
What makes a language convenient? Most people would agree on a declarative
style rather than a imperative style, declaring what you want instead of how
you want it. One such declarative style is practiced among Ruby programmers,
whose programming language lets them write statements that often look almost
exactly like English sentences.

```
File.readlines('file.txt').each do |line| puts line end
```
Could be translated to:
"From the file 'file.txt' read the lines and for each line do the following:
print the line, then end."

Another declarative approach is the mathematical approach seen in functional
programming. Notice the similarities between the haskell function below for
calculating Fibonacci numbers and the corresponding mathematical definition
(and yes, you can do other things in haskell than calculating Fibonacci
numbers, but it fits the purpose).

```
fib n
  | n == 0 = 0
  | n == 1 = 1
  | n > 1 = fib (n - 1) + fib (n - 2)
```

![Fibonacci](/img/fib.png)

Thus, different programming languages take different approaches in describing
abstract thought. Haskell uses mathematics and ruby uses everyday convenience:
it is up to debate which abstraction technique is better. Category theory, on
the other hand, does not fit into this discussion; it lies on a higher level.
Even Haskell, with all its elegance looks like an ugly compared to category
theory.

## A language for Problem solving

We, humans, know of one way of solving complex problems. Given a problem, we:

1. Divide the problem into smaller sub-problems
2. Solve the sub-problems recursively
3. Compose the sub-problems together

Naturally, composition lies at the very heart of problem solving. Object
oriented programming, the most dominant programming paradigm right now, tries
to use composition by modeling the world as objects, consisting of smaller
objects. Modeling and composing the world using objects could seem like a good idea
until you start writing parallel code. You see, the problem with objects is not
information hiding - it is that they hide precisely the wrong thing. Objects
hide (1) mutation of state and (2) the data it shares. Put (1) and (2)
together, and you often end up more often than not with a data race.

The above problem is in the object oriented world "solved" using locks.
Unfortunately, locks do not compose well: You cannot take two correct
lock-based functions, combine them and know that the result is still correct.

Thus, we have to turn to other solutions to write composable programs. The best
current ideas come from functional programming with immutable data structures.
Can Category Theory help us expand these ideas or come up with new ones?

## Motivation for Studying Category Theory

The practical motivation for studying category theory is to discover new,
abstract ideas that can be implemented in a practical programming language.
Take [Edward Kmett's libraries][1] as a very concrete proof that this can be
done.

But there is much more to it than just "materialistic" gains. Looking down from
Category Theory, much that was unique before start to look similar. In
particular, you might start to see similarities between independent
branches of mathematics. IT seems like the same
problem solving strategies have been discovered by different mathematicians,
independently, at different points in time. The famous [Curry-Howard-Lambek
isomorphism][4] is a three-way correspondence between types in programming,
propositions in logic and objects of a Cartesian closed category from Category
Theory. For example, the isomorphism maps programs (in for example Haskell)
to constructive proofs in logics and vice versa.

Was it a coincidence that the most brilliant minds that ever walked on this
earth discovered a theory applicable in every domain? Or perhaps, are we
with the help of Category Theory discovering some deep truth about the
universe? Is mathematics [something built into the earth][5], or is it
something we invent?

In the large majority of our existence on this earth, our brains were fairly
primitive. Only after the cognitive revolution, about 70 000 years ago, did we
start to understand abstract imaginary concepts. Only about 500 years ago
did we start to think scientifically. In the grand scheme of things have our brains
not had the time to evolve into doing programming, logic or other abstract
activities. Composition is the only tool we know of to deal with complex
situations, but it might not be the only one. In any case, we can only see
problems that have the particular structure of being decomposable. With this in
mind, looking from a Category Theorist point of view: no wonder
that many mathematical theories look the same!

Maybe the world can always be split into smaller pieces and our brains have
learned to reflect this property. But as physicist are now pushing the
boundaries to what can be split in the real world, we discover that things
start behaving in very strange ways. Therefore, it might be that composability
is not a fundamental property of nature, but instead of our own mind. In that
case, Category Theory is fundamentally a study on how we think.

[1]: https://github.com/ekmett
[2]: https://www.youtube.com/user/DrBartosz/
[3]: https://bartoszmilewski.com/
[4]: https://wiki.haskell.org/Curry-Howard-Lambek_correspondence
[5]: https://en.wikipedia.org/wiki/Platonism
[6]: https://en.wikipedia.org/wiki/Set_theory
[7]: https://en.wikipedia.org/wiki/Ring_(mathematics)
[8]: https://en.wikipedia.org/wiki/Group_(mathematics)

*To be continued...*
