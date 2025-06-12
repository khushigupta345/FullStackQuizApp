# AngularStarter

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.2.10.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


## Configure Tailwind CSS with Angular

`npm install -D tailwindcss postcss autoprefixer` <br>
`npx tailwindcss init` <br>
`Add the paths to all of your template files in your tailwind.config.js file.`<br>
`/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}` <br>
`Add the @tailwind directives for each of Tailwind’s layers to your ./src/styles.css file.` <br>
`@tailwind base;
@tailwind components;
@tailwind utilities;` <br>

## Tailwind CSS Guide

`https://tailwindcss.com/docs/guides/angular` <br>

To get started, install prettier-plugin-tailwindcss as a dev-dependency:

`npm install -D prettier prettier-plugin-tailwindcss`
