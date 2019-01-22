Development
------------
The actual build of the website will be within the /build folder.

To get started using Sass-lang, be sure to install the required components with the following commands:
```
>npm install -g gulp
>npm install -g node-sass
>npm install
```

To automate the proces of converting the .scss files to .css into the build folder, we will use gulp. In order to use gulp, you can execute the following command in the /Website folder which will automaticly convert the .scss file into .css every time you hit save.
```
>gulp sass:watch
```

Other commands which may be of use:
Run babel builder
```
>npm run build
```

Run single Sass builder
```
>npm run sass
```