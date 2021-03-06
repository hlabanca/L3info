/**************************************************************************
 * L3Informatique						Module SYR1
 * 			TP de programmation en C :
 *		Mise en oeuvre de listes cha�n�es
 *
 * Groupe 	: 2.1
 * Nom Pr�nom 1 : Labanca Hector
 * Nom Pr�nom 2 : Bardou Augustin
 *
 **************************************************************************/

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<list.h>


int nb_malloc=0;

/*
 * SYNOPSYS :
 *   void free_element(list_elem_t* l)
 * DESCRIPTION :
 *   lib�re un maillon de liste.
 * PARAMETRES :
*   l : pointeur sur le maillon � lib�rer
 * RESULTAT :
 *   rien
 */
void free_element(list_elem_t* l) {
  nb_malloc--;
  free(l);
}

/*
 * SYNOPSYS :
 * 	 list_elem_t* create_element(int value)
 * DESCRIPTION :
 *   cr�e un nouveau maillon de liste, dont le champ next a �t� initialis� � NULL, et
 *   dont le champ value contient l'entier pass� en param�tre.
 * PARAMETRES :
 *   value : valeur de l'�l�ment
 * RESULTAT :
 *   NULL en cas d'�chec, sinon un pointeur sur une structure de type list_elem_t
 */
list_elem_t* create_element(int value) {
  nb_malloc++;
  list_elem_t* newelt= malloc(sizeof(list_elem_t));
  if (newelt!=NULL){
    newelt->value=value;
    newelt->next=NULL;
  }
  return newelt;
}


/*
 * SYNOPSYS :
 * 	 int insert_head(list_elem_t** l, int value)
 * DESCRIPTION :
 *   Ajoute un �l�ment en t�te de liste, � l'issu de l'ex�cution de la fonction, *l d�signe la nouvelle t�te de liste.
 * PARAMETRES :
 *	list_elem_t** l : pointeur sur le  pointeur de t�te de liste
 *	int value : valeur de l'�l�ment � ajouter
 * RESULTAT :
 *     0 en cas de succ�s.
 *    -1 si l'ajout est impossible.
 */
int insert_head(list_elem_t** l, int value) {
  list_elem_t* new_elt =  create_element(value);
  if ((l!=NULL) && (new_elt!=NULL)) {
    new_elt->next=*l;
    *l=new_elt;
    return 0;
  } else {
    return -1;
  }
}


/*
 * SYNOPSYS :
 * 	 int list_size(list_elem_t* l)
 * DESCRIPTION :
 *   retourne le nombre d'�l�ments dans la liste
 * PARAMETRES :
 *	 list_elem_t* l : pointeur sur la t�te de liste
 * RESULTAT :
 *        nombre de maillon dans la liste
 */
int list_size(list_elem_t* l) {
  int size =0;
  while (l!=NULL) {
    size++;
    l=l->next;
  }
  return size;
}
 
/*
 * SYNOPSYS :
 * 	 int insert_tail(list_elem_t** l, int value)
 * DESCRIPTION :
 *   Ajoute un �l�ment en queue de la liste (*l d�signe la t�te de liste)
 * PARAMETRES :
 *	 list_elem_t** l : pointeur sur le pointeur de t�te de liste
 *	 int value : valeur de l'�l�ment � ajouter
 * RESULTAT :
 *     0 en cas de succ�s.
 *    -1 si l'ajout est impossible.
 */
int insert_tail(list_elem_t** l, int value) {
	list_elem_t* p = NULL;
	if(l != NULL)
	{
		p = *l;
		while(p->next != NULL)
		{
			p = p->next;
		}
		list_elem_t* nvElt = create_element(value);
		if(nvElt != NULL)
		{
			p->next = nvElt;
		}
		else	return -1;
	}
	else	return -1;

	return 0;
}

/*
 * SYNOPSYS :
 * 	 int remove_element(list_elem_t** ppl, int value)
 * DESCRIPTION :
 *   Supprime de la liste (dont la t�te a �t� pass�e en param�tre) l'element dont
 *   la valeur a �t� pass�e en param�tre, et lib�re l'espace m�moire utilis� par le maillon
 *   ainsi supprim�. Attention, on suppose que value n'appara�t qu'un seule fois dans
 *   la liste, et � l'issu de la fonction la t�te de liste peut avoir �t� modifi�e.
 * PARAMETRES :
 * 	list_elem_t** ppl : pointeur sur le  pointeur de t�te de liste
 * 	       int value  : valeur � supprimer de la liste
 * RESULTAT :
 *     0 en cas de succ�s.
 *    -1 si erreur
 */
int remove_element(list_elem_t** ppl, int value) {
	list_elem_t* p = NULL;
	list_elem_t* prec = NULL;
	if(ppl != NULL)
	{
		p = *ppl;
		do
		{
			if(p->value == value)
			{
				if(p == *ppl)
					*ppl = (*ppl)->next;
				else
					prec->next = p->next;
				free_element(p);
				return 0;
			}
			prec = p;
			p = p->next;
		} while(p != NULL);
	}
	return -1;
}


/*
 * SYNOPSYS :
 * 	 list_elem_t* find_element(list_elem_t* l, int index)
 * DESCRIPTION :
 *   Retourne un pointeur sur le maillon � la position n�i de la liste 
 *   (le 1er �l�ment est situ� � la position 0).
 * PARAMETRES :
 * 	  int index : position de l'�l�ment � retrouver
 * 	  list_elem_t* l : pointeur sur la t�te de liste
 * RESULTAT :
 *    - un pointeur sur le maillon de la liste en cas de succ�s
 *    - NULL si erreur
 */
list_elem_t* find_element(list_elem_t* l, int index) {
  list_elem_t* p = NULL;
	if(l != NULL)
	{
		p = l;
		
		while((p->next != NULL)&&(index != 1))
		{
			p = p->next;
			index--;
		}
		if(index == 1)
		{
			return p;
		}
	}
  	return NULL;
}



/*
 * SYNOPSYS :
 * 	 void reverse_list(list_elem_t** l)
 * DESCRIPTION :
 *   Modifie la liste en renversant l'ordre de ses �lements
 *   le 1er �l�ment est plac� en derni�re position, le 2nd en
 *   avant derni�re, etc.)
 * PARAMETRES :
 * 	  list_elem_t** l : pointeur sur le pointeur de t�te de liste
 * RESULTAT :
 *   aucun 
 */
void reverse_list(list_elem_t** l) {
	list_elem_t* p = NULL;
	list_elem_t* newList = NULL;	
	if(l != NULL)
	{
		p = *l;
		while(p != NULL)
		{
			insert_head(&newList,p->value);
			list_elem_t* temp = p;
			p = p->next;
			free_element(temp);					
		}
		*l = newList;
	} 	
}


