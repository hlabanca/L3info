#!/bin/sh

if 
	test $# -ne 3 -o ! -f $2
then
	echo "Erreur dans les paramètres."
	exit
fi
echo $1 | cat - $2 | /share/l3info/syr1/tp_unix/prog2 > $3

