### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

Main.class : Main.java Grille.class FenetreMenuPrincipal.class SousMenu.class
	${JC} ${JCFLAGS} Main.java

Grille.class : Grille.java Bloc.class
	${JC} ${JCFLAGS} Grille.java

Bloc.class : Bloc.java
	${JC} ${JCFLAGS} Bloc.java

JBloc.class : JBloc.java
	${JC} ${JCFLAGS} JBloc.java

FenetreMenuPrincipal.class : FenetreMenuPrincipal.java FondMenuPrincipal.class ControleurMenuPrincipal.class
	${JC} ${JCFLAGS} FenetreMenuPrincipal.java

FondMenuPrincipal.class : FondMenuPrincipal.java 
	${JC} ${JCFLAGS} FondMenuPrincipal.java

ControleurMenuPrincipal.class : ControleurMenuPrincipal.java 
	${JC} ${JCFLAGS} ControleurMenuPrincipal.java

FondSousMenu.class : FondSousMenu.java 
	${JC} ${JCFLAGS} FondSousMenu.java

FenetreJeuPrincipal.class : FenetreJeuPrincipal.java Grille.class JBloc.class ControleurJeuPrincipal.class
	${JC} ${JCFLAGS} FenetreJeuPrincipal.java

ControleurJeuPrincipal.class : ControleurJeuPrincipal.java Grille.class JBloc.class FenetreMenuDeFin.class
	${JC} ${JCFLAGS} ControleurJeuPrincipal.java

ControleurSousMenu.class : ControleurSousMenu.java FenetreJeuPrincipal.class JBloc.class Grille.class
	${JC} ${JCFLAGS} ControleurSousMenu.java

SousMenu.class : SousMenu.java FondSousMenu.class ControleurSousMenu.class
	${JC} ${JCFLAGS} SousMenu.java

FenetreMenuDeFin.class : FenetreMenuDeFin.java ImageMenuDeFin.class ControleurMenuDeFin.class
	${JC} ${JCFLAGS} FenetreMenuDeFin.java

ImageMenuDeFin.class : ImageMenuDeFin.java
	${JC} ${JCFLAGS} ImageMenuDeFin.java

ControleurMenuDeFin.class : ControleurMenuDeFin.java 
	${JC} ${JCFLAGS} ControleurMenuDeFin.java


### REGLES OPTIONNELLES ###

run : Main.class
	${JVM} ${JVMFLAGS} Main

clean :
	-rm -f *.class

mrproper : clean Main.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###