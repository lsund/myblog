[1]: http://www.stephendiehl.com/posts/production.html

[2]: http://ingesolvoll.github.io/2017/06/22/plain-react-vs-reagent.html

[3]: https://github.com/Gabriel439/post-rfc/blob/master/sotu.md

[4]: http://tech.frontrowed.com/2017/11/01/rhetoric-of-clojure-and-haskell/

[5]: http://www.lispcast.com/clojure-and-types

[6]: /blog/post?id=dynamic_vs_static_p2

[7]: https://www.haskell.org/hoogle/

# Part 3: Choosing your language

[Part 2][6]

*4 minute read.*

## Implications of checking types

In Clojure, it is easy to run into a runtime exception due to a type. In
Haskell, it is virtually impossible to get a runtime exception due to a typo.
One of the main things to get used to in haskell is the handling of sum types.
With sum types (types which can take one of multiple distinct values) the type
checker forces us to handle all cases. For example, there is no concept of null
(nil) in haskell. Instead, you can use the `Maybe a` to describe the situation
where you might have a value of type `a` or not. If you attempt to retrieve a
record from the database, the result can be modeled with `Maybe
DatabaseRecord` if `DatabaseRecord` is the type of your database record. `Maybe
Record` is a disjoint type that can take exactly one of the possible Values,
`Nothing` if the database value retrieval failed or `Just DatabaseRecord` if
the database value retrieval was successful.

Other direct implications include:

* Discovering a library function without having to write it yourself using
  [hoogle][7]
* Knowing the implementation of a function by only reading the type signature. A classic
  example is the type signature `id :: a -> a`, which only can be implemented
  one way.
* Know what a function does only by reading the well-defined name and type
  signature. Consider (Haskell) `chunksOf :: Int -> [a] -> [[a]]`)
  vs (Clojure) `(defn chunks-of [n xs] ...)`. Types often convey essential
  information of the input parameters and return values.
* Knowing when side effects occur and what kind. A function declaration of type
  `fetch :: URL -> AJAX String` makes it pretty clear that an AJAX side effect
  takes place upon returning a String.
* Ability to refactor out a piece of your code, and directly detect if something
  broke.

The things that the type checker gives us are helpful and makes writing most
programs easier. However, the counter-argument is usually that these things
really are not that important in large scale programming projects.

## Programming in the large

Let's consider real programs in the real world. Real world programs:

1. Are not elegant.
2. Run continuously over an extended period of time.
3. Deal with real world irregularity.
4. Interact with other systems.
5. Interact with humans.

This makes it hard to enforce rules and constraints once the system grows.

And now to the biggest problem of software engineering: the misconception of
the problem. This problem applies to all engineers in all fields and paradigms,
and it can not be type checked.

Clojurians argue that it is unfeasible to try to fix peoples misunderstanding
of what they are trying to do with constraints and guardrails. Often, things
that work best nobody knows how to explain, like how to drive a car or how to
play the game go. We use information driven methods such as ML in order to
solve them, not strict logic and enforced rules.

## The difference between a static and a dynamic language

In the real world, information dominates logic. Data is fluid and
unpredictable. In order to deal with that, there are many approaches. These are
the two extremes:

1. Make your program dynamically extensible and open, and let the data come to
   you in its pure form.
2. Attempt to completely understand your data and model your problem depending
   on that.

The question is only where you draw the line. Consider:

If you have a really open system you still have to define a well-defined
interface to be able to do something useful. Otherwise you just know one single
thing about your data: it's existence. At this point you're gradually turning
your program static, and less flexible for changes. This turns into a paradox.
Because of this paradox, we have the clash between the static and the dynamic
world. So how do we choose?

## Choosing between a dynamic and a static programming language

### First attempt: "Use the right tool for the job"

This is a popular saying in the community, but the problem is that this is a
zero-information statement. The point being that it's impossible to know what
you are supposed to do before doing it. So, we rely on experience. But how can
we be sure that what has been done before by someone in our team is the optimal
way to go?

#### Second attempt: Your view on the world

The last resort is to turn philosophical. Platonism is a philosphy that states
that there is a universal truth behind all of mathematics. For example, a
circle drawn on a piece of paper thats not a circle but merely a projection of
the circle in the physical world. A circle is something abstract that does not
originate from neither the physical world or the mental world of our minds. It
exists in a third world, disconnected from the mental and physical world, the
world of truth.

The complementary theory is known as formalism. Formalism states that it is
possible for anyone can sit down with a pen and paper and write down a number
of axioms, from which you derive _all_ of mathematics. As a side-note, Gödel
showed that there are some things in mathematics that cannot be derived like
this. On the other hand, the Curry-Howard-Lambek isomorphism states that
different fields in mathematics have a one-to-one correspondance, ie they are
the same.

Can you identify with one of these philosophies? If you can, this might help
you choose your programming paradigm..

1. You are a Platonist. You believe that mathematics and the universe has a
   universal truth disjoint from our (current) understanding. You shouldn't try
   to set up your own rules and enforce theories of understanding the world,
   instead let the world come to you in a free form.  Use Clojure as your tool
   as it helps you describe the world without restrictions.

2. You are a Formalist. You believe that the fact that the "same" math was
   discovered at different places at different times shows that there is some
   inherent structure in the world of problem solving. We make our own mental
   models and we change them as needed. Use Haskell as your tool as it is
   superior in describing this structure.

No matter what thoughts and viewpoints you have today: Remember to stay open!
As craftsmen, we are really proud on our knowledge and our craft and values.
However, it is when we expand our mind that we can elevate ourselves to the
next step.

## Some thoughts were based on the following

[Stephen Deihls Haskell in production][1]

[Base information about reagent][2]

[Haskell Pros and Cons][3]

[Haskell vs Clojure (A Haskell bloggers standpoint)][4]
[Haskell vs Clojure (A Clojure bloggers standpoint)][5]
