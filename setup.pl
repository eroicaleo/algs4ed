#!/usr/bin/env perl

use warnings;
use strict;
use 5.014;

use Env;
use File::Copy;

use Env qw(PWD HOME);

mkdir "./bin" unless -d "./bin";
system("wget http://algs4.cs.princeton.edu/linux/drjava.jar") unless -f "drjava.jar";
unless (-f "bin/drjava") {
  system("wget http://algs4.cs.princeton.edu/linux/drjava");
  chmod 0700, "drjava";
  move "drjava", "./bin";
}

system("wget http://algs4.cs.princeton.edu/code/stdlib.jar") unless -f "stdlib.jar";
system("wget http://algs4.cs.princeton.edu/code/algs4.jar") unless -f "algs4.jar";

unless (-f "bin/javac-algs4") {
  system("wget http://algs4.cs.princeton.edu/linux/javac-algs4");
  chmod 0700, "javac-algs4";
  move "javac-algs4", "./bin";
}

unless (-f "bin/java-algs4") {
  system("wget http://algs4.cs.princeton.edu/linux/java-algs4");
  chmod 0700, "java-algs4";
  move "java-algs4", "./bin";
}

system("wget http://algs4.cs.princeton.edu/linux/checkstyle.zip") unless -f "checkstyle.zip";
system("wget http://algs4.cs.princeton.edu/linux/findbugs.zip") unless -f "findbugs.zip";
system("unzip checkstyle.zip");
system("unzip findbugs.zip");
system("wget http://algs4.cs.princeton.edu/linux/checkstyle.xml");
system("wget http://algs4.cs.princeton.edu/linux/findbugs.xml");
system("wget http://algs4.cs.princeton.edu/linux/checkstyle-algs4");
system("wget http://algs4.cs.princeton.edu/linux/findbugs-algs4");
chmod 0700, "checkstyle-algs4" ;
chmod 0700, "findbugs-algs4";
move "checkstyle-algs4", "./bin";
move "findbugs-algs4",   "./bin";
move "checkstyle.xml",   "checkstyle-5.5";
move "findbugs.xml",     "findbugs-2.0.3";

symlink "${PWD}", "${HOME}/algs4" unless "${PWD}" eq "${HOME}/algs4";
