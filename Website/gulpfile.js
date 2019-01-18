'use strict';
var gulp = require('gulp');
var sass = require('gulp-sass');

gulp.task('sass', function(done){
	gulp.src('./dev/sass/**/*.scss')
		.pipe(sass().on('error', sass.logError))
		.pipe(gulp.dest('./build/css'));
		done();
});

gulp.task('sass:watch', function(){
	gulp.watch('./dev/sass/**/*.scss', gulp.series('sass'));
});