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
