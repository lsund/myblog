[1]: /blog/post?id=dynamic_vs_static_p1
[2]: https://wiki.haskell.org/IDEs
[3]: cognitect
[4]: /blog/post?id=dynamic_vs_static_p3

# Part 2: The Core Ideas

[Part 1][1]

*4 minute read.*

## Core ideas: Clojure

### #1

The first and most important core idea of Clojure is simplicity. The
[maintainers][3] of Clojure and believe that a lot of the complexity in todays
software development is "accidental" and a product of the human disability to
model the world around us. Clojure aims to get rid of that complexity.

The idea is to leave out complex programming constructs and replace them with
simpler constructs. In a classical functional programming spirit, programs are
described in idiomatic Clojure by key-value maps, and pure functions to operate
on those. The language design is focused on the simplicity of pure data.
Enforced wrappers around data like object or static types are not simple.

However, Clojure won't force you to only write code using pure functions or use
mathematical abstractions. There might be a strong consensus on how to work in
Clojure but the language itself does not force you to design your interfaces in
any particular way. This freedom usually means that you have to do more
planning up front. Clojure thus encourages the thinking part of software
development and lays a lot of responsibility on the individual programmer to do
whats right in any given situation.

To give an example of how this 'freedom', consider the core library
`clojure.spec`.  Clojure does not have any static types, but it allows the
programmer to to opt into using types and contracts at any point you feel like
it will enhance your program.  Similarly, you can implement an interface for
monads in Clojure, but nobody is going to tell you that you can't use Clojure
if you don't know monads.

### #2

The second core idea is that Clojure is a hosted language, meaning it does not
have a runtime platform developed specifically for itself. Clojure uses other
platforms to execute the code, such as the JVM, CLR or the browser. This has
multiple advantages. First, the maintainers of Clojure can focus on the
language instead of compilers and other tools. Also, this means that Clojure is
really just a library, not an ecosystem, which makes it portable. If you know
Clojure, you can write your code in Java, Javascript and .NET environments with
minimal overhead.

Specifically, consider the `.cljc` extension for Clojure source files. The
`cljc` extension allows you to write a single source file that acts like any
Clojure code independent on the runtime environment. You can thus copy all your
sources between the different environments (such as front-end/back-end). This
is probably how all programming will be done in the future. "Write once, run
on any compiler".

### #3

Another important thing about Clojure is that it is a Lisp. Being a Lisp means
that code is data (and the converse: data is code). Clojure does
not have any syntax, and there is no magical rules about how text is compiled
into a program. There is a one to one correspondence between the source code
and the Clojure data structures, so you are editing the data structures
themselves directly.

In modern software development, there are way too many different frameworks
which all specify their own way of writing code. As a result, many new
developers have a hard time understanding the technology they are using. By
removing the separation between data structures and the text entered into the
text editor, lisps greatly simplifies the act of programming.

## Core Ideas: Haskell

### #1

Haskell has a strong static type-system which means that it is hard to get
runtime errors. To obtain freedom and elegance while maintaining this safety,
Haskell makes use of very few, but very powerful abstractions. Many of
Haskell's abstractions comes from category theory, a branch of abstract
mathematics, focused on the most fundamental parts of problem solving:
composition and identity. By using these abstract concepts, the passion of many
haskellers lie in writing beautiful code, maybe more than any other programming
language.

### #2

Haskell programmers model their problem domain in terms of types and pure
functions based on those types. Applying type driven development in this way,
you're often doing a lot planning up-front, so haskellers and clojurians,
have to think before writing code. The difference is that haskellers are
actually forced to do so or their programs wont compile.

Since types is a fundamental part of Haskells design, the compilers
type-checker is helping you. This is in contrast to the feeling that many
programmers in get in imperative static languages that often feel that the type
checker is working against them. Haskells type checker has type-inference,
meaning that you don't explicitly have to state the type of your definitions,
instead the compiler infers the most generic type.  Types imply a quiet agreement
between you and the compiler on how the code should be structured, and hence,
haskell knows how you think. If you get a compile error in Haskell, something
is often wrong with your thinking.

### #3

Haskell is lazy, meaning that by default, evaluation of an expression is
deferred until other parts of the program need the result of the expression .
If you're thinking about computation as a tree, starting with the main function
at the root, calling other functions in subsequent tree nodes and so on,
Haskell evaluates this tree top down, disregarding any paths in the tree which
isn't needed to derive the top level result. A strict language with eager
evaluation of function arguments would have to evaluate all branches and paths
of this tree.

Another advantage you get from laziness is flexibility when working with large or
possibly infinite data-structures. In Haskell, its common to apply some function
to an infinite list and select a subset of the resulting values dynamically
depending on the runtime environment of the program.

## Haskell: Disadvantages

Haskell excels in error-prone applications. Common examples of such
applications are compilers and parsers, but Haskell is nowadays used in
industry to some extent.. However, there are some problems with adaption that
many other languages do not have.

#### 1. Lack of proper IDE

There are actually quite a few [possibilities][2] for achieving some kind of
integrated development environment for Haskell. Some IDE's like Eclipse have
plugins for Haskell, but it's nothing like the IDE experience you're used to as
a Java or C# developer.

Programming functionally is different than Object Oriented programming, and you
definitely don't need an IDE. The language Haskell is constructed in such a way
that you don't need many additional tools to help with organization, access and
refactoring of the code.  Most of the time, this comes naturally with good
coding practice. However, this can be hard to accept for newcomers and for
people used to IDE's, which can be discouraging.

#### 2. Bindings to commercial languages often not existing

If you want to go ahead and connect to Microsoft SQL server, you're probably
going to have to do some extra work. In mainstream languages like Python
chances are good you'll find a off-the-shelf-solution for basically anything
you want to do. In Haskell, you have to be prepared to do more work. It often
comes down to, if you are not going to write that library, nobody else will.

#### 3. Memory usage difficult to reason about due to laziness

Both memory usage and execution time can be hard to formally reason about since
you can't know for sure which parts of your program that will be executed when.
Since haskell was not designed for commercial usage, the language designers
did not put too much consideration to this problem.

#### 4. Lack of documentation because of the fact that we have types signatures

A lot of authors think that well named functions and their type signatures are
enough documentation. It's not uncommon that entire "documentation" pages
contain nothing but type signatures. This is a problem, especially for
beginners trying to approach Haskell.

#### 5. You have to learn mathematical abstractions (AKA steep learning curve)

Another problem with haskell is illustrated by discussing Elm programming
language. Elm is on first glance a very Haskell-like language, its syntax is
practically indistinguishable with Haskell's. However, Elm chooses to leave out
some important features that make Haskell powerful, for example higher kinded
types. With no higher kinded types, you can't for one thing define a common
interface for types that can be applied to some function, for example a map. In
Haskell, you can call the map-function on a value if its type is instance of
the typeclass Functor, plain and easy. In Elm, there are no type classes, and
as a result, you have to do List.Map, Map.Map, Array.Map etc...

Does things like this stop people from using from using Elm? No! Elm has become
popular and is probably more widely adopted than stronger languages such as
PureScript. The consensus? People don't want to know what a functor is. More
generally: people feel that in order to use Haskell, you have to learn what a
functor is, then Applicative, then monoid until you finally get Monad-epiphany,
at which point you can start actually programming in haskell.  Since nobody has
time to climb this mountain of knowledge, Haskell does not get enough love. It's
clearly discouraging using a language in which you only understand the basics.

Of course, the mountain is easier to climb that most people think. Once the
basic ideas of haskells abstractions can be explained and understood, the rest
will follow quite easily. Take monoid, which is a very simple concept. monoid
is the class of all types which fulfill the idea that you can append data
together in an associative way. Associativity means if you have three
appendable items after each other, it does not matter in which order you append
them to each other. It will produce the same result.

As a result, when we are talking about monoids, we can have a high-level
conversation about the properties of our program.

*- Hey, I've spent the afternoon on implementing the monoid class for our array
type.*

*- Nice! Then I know I can recursively descend into it or even parallelize it
safely.*

It would not be very misleading to rename monoid to appendable. But then, would
you at the same time rename integer to 'addable? A monoid is a monoid, an
integer is an integer.

[Part 3][4]
