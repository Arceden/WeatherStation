'use strict';
var gulp = require('gulp');
var sass = require('gulp-sass');
var minify = require('gulp-babel-minify');

gulp.task('sass', function(done){
	gulp.src('./dev/sass/**/*.scss')
		.pipe(sass().on('error', sass.logError))
		.pipe(gulp.dest('./build/css'));
		done();
});

gulp.task("minify", () =>
	gulp.src("./build/js/*.js")
		.pipe(minify({
			mangle: {
				keepClassName: true
			}
		}))
		.pipe(gulp.dest("./build/js"))
);

gulp.task('sass:watch', function(){
	gulp.watch('./dev/sass/**/*.scss', gulp.series('sass'));
});