#!/usr/bin/env perl

use warnings;
use strict;
use 5.014;

use File::Copy;

mkdir "./bin" unless -d "./bin";
system("wget http://algs4.cs.princeton.edu/linux/drjava.jar") unless -f "drjava.jar";
system("wget http://algs4.cs.princeton.edu/linux/drjava") unless -f "bin/drjava";
chmod 0700, "drjava";
move "drjava", "./bin";

system("wget http://algs4.cs.princeton.edu/code/stdlib.jar") unless -f "stdlib.jar";
system("wget http://algs4.cs.princeton.edu/code/algs4.jar") unless -f "algs4.jar";
system("wget http://algs4.cs.princeton.edu/linux/javac-algs4") unless -f "bin/javac-algs4";
system("wget http://algs4.cs.princeton.edu/linux/java-algs4") unless -f "bin/java-algs4";
chmod 0700, "javac-algs4";
chmod 0700, "java-algs4";
move "javac-algs4", "./bin";
move "java-algs4", "./bin";

# system("wget http://algs4.cs.princeton.edu/linux/checkstyle.zip") unless -f "checkstyle.zip";
# system("wget http://algs4.cs.princeton.edu/linux/findbugs.zip") unless -f "findbugs.zip";
# system("unzip checkstyle.zip
# system("unzip findbugs.zip
# system("wget http://algs4.cs.princeton.edu/linux/checkstyle.xml
# system("wget http://algs4.cs.princeton.edu/linux/findbugs.xml
# system("wget http://algs4.cs.princeton.edu/linux/checkstyle-algs4
# system("wget http://algs4.cs.princeton.edu/linux/findbugs-algs4
# [username:~/algs4/] chmod 700 checkstyle-algs4 findbugs-algs4
# [username:~/algs4/] mv checkstyle-algs4 bin
# [username:~/algs4/] mv findbugs-algs4 bin
# [username:~/algs4/] mv checkstyle.xml checkstyle-5.5
# [username:~/algs4/] mv findbugs.xml findbugs-2.0.1
