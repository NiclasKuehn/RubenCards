#!/bin/bash
DIR=$2
echo "-----------START-----------"
if [ "$1" = "add" ] && [ "$3" -ge "2" ] && [ "$3" -le "10" ]
then
        echo "add wird ausgeführt"
        while [ -d DIR ]
                do
                        echo "Verzeichnes existier bereits ... GEBEN SIE EIN NEUES VERZEICHNIS EIN"
                        read DIR
                done
        echo "Gruppe $DIR wurde erstellt"
        groupadd $DIR

        
        for ((i=1;i<=$3;i++))
        do
                echo „Geben sie den Namen für den $i . Benutzer ein“
                read USER
                useradd $USER -m -b $DIR -g $DIR -s /bin/bash
                echo $USER:$USER | chpasswd
                echo "$USER wurde erstellt"
                for j in $(id -G user)
                do
                        usermod -aG $j $USER
                        echo "$USER wurde zur Gruppe mit ID : $j hinzugefügt"
                done
                echo "$USER ist nun in allen Gruppen wie der Benutzer"
        
        done
        echo "Alle Benutzer wurden erstell"
fi

if [ "$1" = "delete" ]
then
        echo "delete wird ausgeführt"
        if [ -d $DIR ]
        then
                echo "User werden gelöscht:"
               for  i in $(ls $DIR)
                do
                        echo "USER $i wird gelöscht"
                        userdel $i
                done
                echo "Verzeichnis $DIR wird gelöscht"
                rm -r $DIR
                echo "Gruppe $DIR wird gelöscht"
                groupdel $DIR

        else
                echo "Verzeichnes ( $DIR )  nicht gefunden"
        fi