I'm adapting the template for use in LD jams, making it behave similarly to the Super Fast Framework for Pico 8.
It'll include:
1. predefined screens (Title, Game Over, Win Screen, ...)
1. Pico 8 font, screen resolution and color palette
1. Simple colission detection
1. Tiled integration (pending)
1. Tweening Library (pending)

# libGDX template

Starter project for libGDX featuring easy asset loading and a scaled pixel-art effect. Supports both desktop and html distributions, making it perfect for Game Jams.

To get started:

```
$ git clone https://github.com/halfcutdev/libgdx-template
$ cd libgdx-template
$ ./gradlew desktop:run
```

#### Building the HTML distribution
People are much more likely to play your game if they can do so in the browser. To build a HTML version of your game, go to your project root and run:
```
$ ./gradlew html:dist
```
Now go to the `html/build/` directory and locate the folder called `dist`. Zip this up and upload it to a site like [itch.io](https://itch.io/), and your game will be playable in the browser. Just remember to change the title of the web page from *'libGDX template'* to the name of your game. You can do this by changing the contents of the `<title>` tag in `html/webapp/index.html`.

**Note** - Building a HTML dist is *slow*. During development, you should use `./gradlew html:superDev` which allows you to debug your code directly in the browser. More information about this is available on the [libGDX Wiki](https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline#running-the-html-project).

---

Created by [halfcutdev](https://github.com/halfcutdev)
