[1]: /blog/post?id=dynamic_vs_static_p1
[2]: https://wiki.haskell.org/IDEs
[3]: cognitect
[4]: /blog/post?id=dynamic_vs_static_p3

# Part 2: The Core Ideas

[Part 1][1]

**Target audiance**: People who likes programming languages - *4 minute read.*

## Core ideas: Clojure

### #1

The core idea of Clojure is simplicity. The [creators][3] of Clojure and related
technologies believe that a lot of the complexity in todays software development
is "accidental" and a product of the human disability to model the world around
us. Clojure aims to get rid of that complexity.

The idea is to leave out the elements in programming that are complex and
replace them with simple things. For example, objects are replaced in Clojure
by key-value maps, methods with functions etc. Basically, it boils down the
idea that data itself is simple. Any kind of wrappers around data, like object
or types around data are not simple. As a result, idiomatic Clojure makes heavy
use of constructs that deal directly with data, like pure functions and
immutable key-value maps.

However, Clojure won't force you to only write code using pure functions or use
mathematical abstractions. Clojure may be an opinionated language (meaning that
there is a strong sense on how to streamline your workflow) but the language
itself does not force you to design your interfaces in any particular way. This
freedom usually means that you have to do some planning up front, especially
when designing larger systems. Clojure thus encourages the thinking part of
software development and lays a lot of responsibility on the individual
programmer to do whats right in any given situation.

For example, Clojure does not have types, but you can use the library
Clojure.Spec to opt in using a type contract at any point you feel like it will
enhance your program. Similarly, you can implement an interface for monads in
Clojure, but nobody is going to tell you that you can't use Clojure if you
don't know monads.

### #2

The second core idea is that Clojure is a hosted language, meaning it does not
have a runtime platform developed specifically for Clojure. Clojure uses other
platforms to execute the code, such as the JVM or the CLR. This has multiple
advantages. First, the developers of Clojure could focus on the language
instead of compilers and other generic tools. Also, this means that Clojure is
really just a library, not an ecosystem, which makes it portable. If you know
Clojure, you can write your code in Java, Javascript and .NET environments with
minimal overhead.

For example, consider the `.cljc` extension for Clojure source files. The
`cljc` extension allows you to write a single source file that acts like any
Clojure code independent on the runtime environment. You can thus copy all your
sources between the different environments (such as front-end/back-end). This
is probably how all programming will be done in the future. "Write once, run
on any compiler".

### #3

Another important thing about Clojure is that it is a Lisp. Being a Lisp means
that code is data (or better: data is code). Clojure does not have any syntax,
and there is no magical rules about how text is compiled into a program.
There is a one to one correspondence between the source code and the Clojure
data structures, so you are editing the data structures themselves directly.

In modern software development, there are way too many different frameworks
which all specify their own way of writing code. As a result, many new
developers have a hard time understanding the technology they are using.
Clojure simplifies the actual meaning of writing code.

## Core Ideas: Haskell

### #1

Haskell has a strong static type-system which means it's harder to get runtime-
errors than to not get runtime errors. To obtain freedom and elegance while
maintaining this safety, Haskell makes use of very few, but very powerful
abstractions. Many of Haskell's abstractions comes from category theory, a
branch of mathematics that abstracts mathematics through composition.
Naturally, haskellers are good at composing functions and various data
structures Since programming is about dividing problems into smaller parts and
then composing them back together again, these abstractions can be invaluable.

### #2

Haskell programmers model their problem domain in terms of types and pure
functions based on those types. Applying type driven development in this way,
you're often doing a lot planning up-front. So both haskellers and clojurians,
have to think before writing code, the difference being that haskellers are
actually forced to do so or their programs wont compile.

Since data-types are core in your program design, the type checker is helping
you, not working against you. This is in contrast to the feeling that many
imperative programmers in get in static languages like Java. They often feel
that the type checker is working against them. The difference is
type-inference. While in haskell everything has a strict type, you don't need
to explicitly state it. The compiler can infer the most generic type for you,
you basically don't need to tell it anything if you don't want to. It's like a
quiet agreement between you and the compiler on how the code should be
structured, and hence, haskell knows how you think. If you get a compile error
in Haskell, something is wrong with your thinking.

### #3

Haskell is lazy, meaning that by default, computation is deferred until the
absolute latest point in time possible. If you're thinking about computation as
a tree, starting with the main function at the root, calling other functions in
subsequent tree nodes and so on, Haskell evaluates this tree top down,
disregarding any paths in the tree which isn't needed to derive the top level
result. A strict language with eager evaluation of function arguments would have
to evaluate all branches and paths of this tree.

Another advantage you get from laziness is flexibilty when working with large or
possibly infinite data-structures. In Haskell, its common to apply some function
to an infinite list and select a subset of the resulting values dynamically
depending on the runtime environment of the program.

## Haskell: Disadvantages

Haskell excels in error-prone applications. Common examples of such
applications are compilers and parsers, but Haskell is growing and gets used in
all kind of applications nowadays. However, there are some problems with
adaption that other languages might not have.

#### 1. Lack of proper IDE

There are actually quite a few [possibilities][2] for achieving some kind of
integrated development environment for Haskell. Some IDE's like Eclipse and
TODO have a plugin for Haskell, but it's nothing like the IDE experience you're
used to as a Java or C# developer.

Programming functionally is different than OO, and you definately don't need an
IDE. The language Haskell is constructed in such a way that you don't need many
additional tools to help with organization, access and refactoring of the code.
Most of the time, this comes naturally with good coding practice. However, this
can be hard to accept for newcomers and for people used to IDE's, this can be
discouraging.

#### 2. Bindings to commercial languages often not existing

If you want to jump in and connect to Microsoft SQL server, you're probably
going to do some extra work. In mainstream languages like Python chances are
good you'll find a off-the-shelf-solution for basically anything you want to
do. In Haskell, you have to be prepared to do more work. It often comes down
to, if you are not going to write that library, nobody else will.

#### 3. Memory usage difficult to reason about due to laziness

Both memory usage and execution time can be hard to formally reason about since
you can't know for sure which parts of your program that will be executed when.
Since haskell was not designed for commercial usage, the language designers
overlooked this problem. The fact that Haskell is growing and being used in
industry is a seperate issue.

#### 4. Lack of documentation because of the fact that we have types signatures

A lot of authors think that well named functions and their type signatures are
enough documentation. It's not uncommon that entire "documentation" pages
contain nothing but type signatures. This is a problem, especially for
beginners coming to Haskell.

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
Purescript. The consensus? People don't want to know what a functor is. More
generally: people feel that in order to use Haskell, you have to learn what a
functor is, then applicative, then monoid until you finally get monad-epiphany.
And since nobody has time to climb this mountain of knowledge, Haskell does not
get adopted. It's clearly discouraging using a language in which you only
understand the basics.

Of course, the mountain is easier to climb that most people think. The problem
is that we are not used to thinking about the functional concepts of category
theory. Take Monoid, which is a very simple concept. Monoid is the class of all
types which fulfill the idea that you can append data together in an
associative way. Associativity means if you have three appendable items after
each other, it does not matter in which order you append them to each other. It
will produce the same result.

As a result, when we are talking about monoids, we can have a high-level
conversation about the properties of our program.

*- Hey I've spent the afternoon on this new monoid for our project.*

*- Nice! Then I know I can recursively descend into it or even parallelize it
safely.*

It would not be very misleading to rename monoid to appendable. But then, would
you at the same time rename integer to addable? A monoid is a monoid, an
integer is an integer.

[Part 3][4]
