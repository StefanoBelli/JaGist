[![Travis](https://img.shields.io/travis/StefanoBelli/JaGist.svg)]() [![GitHub tag](https://img.shields.io/github/tag/StefanoBelli/JaGist.svg)]() [![Copyleft](https://img.shields.io/badge/copyleft-withlove-red.svg)]()

# JaGist
GitHub Gist Java APIs

### Build
You only need gradle:

~~~
 $ gradle build
~~~

This will automatically generate jar compressed package and classes bytecode to *build/* directory.

If *org.json* is not availible, then Gradle will automatically fetch it for you.

### Documentation

GitHub Pages is up!
[Check here!](https://stefanobelli.github.io/JaGist/html)

Alternatively, generate docs your own:

~~~
 $ doxygen Doxyfile
 $ cd docs/html
 $ open index.html
~~~

### Compatibility

CI builds this with 2 different Java versions (8,7) and 3 different JDKs:
 
 * Oracle JDK 8

 *Oracle/Open JDK 7 removed because of Base64 class could not be found*

And it is guranteed to work, doesn't work with Java 6
