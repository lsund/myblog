[1]: https://en.wikipedia.org/wiki/David_Turner_(computer_scientist)
[2]: https://en.wikipedia.org/wiki/Miranda_(programming_language)
[3]: https://en.wikipedia.org/wiki/Haskell_(programming_language)
[4]: https://www.infoq.com/presentations/Simple-Made-Easy
[5]: https://www.youtube.com/watch?v=ROor6_NGIWU
[6]: https://www.youtube.com/watch?v=f84n5oFoZBc
[7]: https://www.youtube.com/watch?v=f84n5oFoZBc
[8]: https://www.doctronic.de
[9]: https://clojure.org/
[10]: http://2017.clojure-conj.org/
[11]: /blog/post?id=dynamic_vs_static_p2
[12]: https://www.youtube.com/watch?v=2V1FtfBDsLU

# Part 1: Introduction

**Target audiance**: People who likes programming languages - *4 minute read.*

**These posts are based on the presentation I had at the Clojure meetup group
in Bonn, Germany. The objective was to give a high-level introduction to
dynamic and static programming languages using Haskell and Clojure to
illustrate. It was all to a standpoint of functional programming, so some
knowledge about functional programming is assumed.**

## The talk: Introduction

I just had an internship here at a small company called [Doctronic][8] where I
spent a couple of months writing [Clojure][9]. I've seen how fast and fast and
painless you can develop *real* systems with Clojure, that just works well.
However, I've always had an affection for strong, static languages like
Haskell.

At [Clojure conj 2017][10], Rich Hickey (the creator of Clojure) said quite a
few controversial statements about static typing and specifically about
Haskell. This stirred up the sleeping debate about static vs dynamic languages.
What kind of programming is better?

Just a recap: Statically typed languages check the types of your variables at
compile-time and dynamically typed languages check them at runtime. This might
seem like a minor detail for some people, but the choice
between static or dynamic typing has a lot of other implications on the
language. To illustrate this, I'm going to use Clojure and Haskell. These are
two great functional languages - both focusing on data and purity. However,
they are different in almost every other aspect. I'm going to underline what
makes Clojure and Haskell special, and which one might fit you better. I'll
start from the beginning.

## Origins and Creators

### Clojure

How did Clojure begin? The answer is: Rich Hickey. Rich had written concurrent
programs in Java and C++ for 18 years and he'd had enough of that. He had an
idea which later became Clojure, a better way to write programs.

At first, his aspirations were not spectacular. During the keynote at the
Clojure conj I mentioned above, he started with [saying][12] that:

*"10 years ago, Clojure was released. ... I told my wife: If a hundred people
used this, that'd be ridiculously outrageous. And that's not what happened."*

After working on Clojure on a while, Rich quickly realized that Clojure was an
idea worth going for. A couple of years after starting out, Rich made the
language public domain and started traveling around talking about Clojure.

Clojure has become successful because of a number of reasons. But the initial
success was partly because on Rich Hickeys excellent public speaking skills. If
you haven't seen his talks, go watch them on youtube. Some great examples are
[Simple made easy][4], [The language of the system][5], [Hammock driven
development][6] and [The value of values][7]. Most of them are about
programming in general and not necessarily about Clojure.

### Haskell

Haskell is a completely different story, directly based on decades on language
research and prior languages. In 1985, the work of [David Turner][1] had
culminated in a programming language called [Miranda][2]. At this time, an
interest in strongly typed lazy functional languages were growing in general.
In 1987 there existed more than a dozen such languages, and these mainly used
for exactly the same reason - to serve as a medium in various research papers.

As a consequence, almost all articles on functional programming and computing
had to dedicate the first paragraphs describing the language they were going to
use. To solve this "problem", a committee were formed at the conference on
Functional Programming Languages and Computer Architecture FPCA '87. The purpose
of this committee to was to create a unified language that would serve as a tool
for future research.

This language became Haskell, and was a collaborative effort among many people
over an extended amount of time. A lot of the people who contributed to Haskell
were and are computer scientists very famous in their respective areas. See
the wikipedia page on [Haskell][3] for details.

Today, Haskell is commonly accepted to be the de-facto standard for lazy,
strong, static functional programming languages, and we see many modern
languages such as Elm, Idris or Purescript deriving from Haskell, both in terms
of syntax and semantics.

However, Haskell officially remains a experimental research language. The
ironic slogan which is often cited in Haskell contexts reads 'Avoid success at
all costs'.

[Part 2][11]

*Updated 3rd Feb 2018*
