# SYR1 : TP 2-3 listes
# Groupe 2.1
# Hector Labanca - Augustin Bardou
#
# Macro debug
#
CFLAGS = -g

#
# Edition de lien
#
bin/test_list : obj/list.o obj/test_list.o
	gcc $(CFLAGS) -Wall -o bin/test_list obj/test_list.o obj/list.o


#
# Modules objets du TP Listes
#

obj/list.o : src/list.c include/list.h
	gcc $(CFLAGS) -Wall -I./include -c src/list.c -o obj/list.o


	

obj/test_list.o : src/test_list.c include/list.h
	gcc $(CFLAGS) -Wall -I./include -c src/test_list.c -o obj/test_list.o

#
# Test automatique
#
tester: bin/test_list
	echo "SYR1 TP 2-3 Résultat du test. Groupe 2.1 Hector Labanca - Augustin Bardou" > resultat_test.txt
	./bin/test_list < commandes.txt >> resultat_test.txt
	cat commandes.txt resultat_test.txt > test_liste.txt

#
# Génération du listing en pdf
#
listing:
	a2ps -o listing.ps include/list.h src/list.c src/test_list.c
	ps2pdf listing.ps
	rm listing.ps

#
# Nettoyage des modules
#
clean :
	rm obj/*.o bin/test_list

