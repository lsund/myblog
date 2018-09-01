# Managing your passwords with pass

[1]: https://www.passwordstore.org/
[2]: https://en.wikipedia.org/wiki/GNU_Privacy_Guard"
[3]: https://wiki.archlinux.org/index.php/GnuPG"
[4]: https://www.archlinux.org

**Target audiance**: Anyone who wants to get more organized - *4 minute read.*

## Instroduction

[pass][1] is a simple password manager for UNIX. It complies with the
traditional UNIX philosophy because it does one thing, and it does it well. It
manages your passwords for you. It does this by using familiar commands such as
`mv` or `tree`. If you want to change the name of a password, you can for
example type `pass mv /old/password/path /new/password/path`. pass is actually
a relatively simple shell-script that uses these standard command line
procedures internally. The secret sauce is a  directory `~/.password-store`
where it keeps your encrypted passwords in a traditional unix directory tree.

I use pass not only for generating random passwords, also for pin-codes,
card numbers, usernames or any string that need to be persisted and remembered.
It makes my life more organized, and it helps me keeping my sensitive data
secure and available. The other (possibly safer?) method of organizing your
passwords is writing everything down on a standard sheet of paper. But this
piece of material can easily be lost/discovered or forgotten. I'll show you
how to safely store your password with pass and safely share this information
between different machines.

## Generating keys

pass encrypts your passwords using [GPG][2]. That means in order to use pass,
you'll have to generate a private/public key pair if you don't already have
such a key pair. Since I'm using [Arch Linux][4], I browsed the [archwiki][3]
to read about how to generate a key pair on my system. Once you have your key
pair installed, you can list it with `gpg --list-keys`.

You'll get an output that looks something like the following:

```
/home/lsund/.gnupg/pubring.kbx
------------------------------
pub   rsa4096 2017-02-18 [SC]
    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
uid           [ultimate] Ludvig Sundström <my@email.com>
sub   rsa4096 2017-02-18 [E]

...
```

And for the secret keys: `gpg --list-secret-keys`

```
/home/lsund/.gnupg/pubring.kbx
------------------------------
sec   rsa4096 2016-06-01 [SC]
    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
uid           [ultimate] Ludvig Sundström <my@email.com>
ssb   rsa4096 2016-06-01 [E]

...
```

The ID of the keys looks like a hashed string. In this example, I've replaced
the ID of my keys with X's. It's possible that the ID of your keys on your
computer will look different, be shorter/longer/at a different place in the
output etc.

Determine the ID's of your keys and copy them. You can now initialize your
password store using pass with your keys.

```
    pass init "my private key ID".
```

Refer to [pass][1] for how to add new and manage your passwords.

## password-store as a git repository

If you want to manage passwords on different computers, it's not a bad idea to
put your encrypted `~/.password-store` in a public git-repository such as
Github. Since all  password files in `.password-store` are encrypted and it
doesn't contain any of your keys, this can be considered safe.

The weak link is always your private key. If someone gets hold of your private
key, that person also get's access to all your passwords and personal details
you store with pass. Make sure you choose a strong password when generating it
and never show it to anyone.

To extend your password-store into a git repository, use

```
pass git init
```

Now every time you add or modify a password, it will be logged as a commit, and
you can use good old push/pull to sync your pass repository between computers.

## Moving the keypair

You can have different keys for the same pass repository, but you can also use the
same key on different computers. You greatly increase your risk though, since
if one machine gets compromised, you loose everything. Loosing all your
passwords can be more devastating to your life than you think! You should only
share keys and keep a password store on computers that only you use.

There are different ways of moving a key pair to another computer, but one way
is to safely transfer the files over (preferably local) network using `scp`.
First export the files. On Arch Linux, you can do:

```
gpg --output public.key --armor --export <user-id>
gpg --export-secret-keys --armor <user-id> > privkey.asc

scp privkey.asc remoteuser@remotehost:/some/path/privkey.asc
scp privkey.asc remoteuser@remotehost:/some/path/public.key
```

Here, `<user-id>` is the id of the owner of the key, in my case the email
displayed next to my name when listing the keys.

Now, on the remote (other) computer, do

```
cd /some/path

gpg --import public.key
gpg --import privkey.asc

shred privkey.asc
rm privkey.asc
rm public.key
```
The private key is shredded in order to remove any traces of it on disk.
Your passwords are now synced and safe between your two computers using
standard git pull and push.
