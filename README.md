# Funkcionalni zahtevi
**Korisnik** 

    - id  
    - username      
    - email      
    - devices  
    
    Akcije korisnika  
        - Kreiranje korisnika (registracija)  
        - Izmena podataka  
        - Pristup aplikaciji (login)  
        - Brisanje  
        
        Moze da se registruje i uloguje na sistem  
            doda i izmeni svoje uredjaje  
            prati lokaciju svih svojih uredjaja  
                - lokacija se cuva onog trenutka kada korisnik pokrene tu opciju  
                - svaki uredjaj ima listu lokacija organizovanih po datumu  
                - korisnik moze da pristupi listi lokacija uredjaja po deviceId + date
                                    
            kreira grupu  
                - korisnik koji kreira grupu, automatski postaje njen administrator  
                - grupa ce prikazivati listu uredjaja sa njihovim lokacijama  
                - uredjaj moze da se doda i izbaci iz grupe samo od strane administratora                  
                - svaki korisnik u grupi ce deliti svoju lokaciju, i lokaciju svih svojih uredjaja  
                - da napusti grupu

**Lokacija**  

    - id  
    - latitude  
    - longitude  
    - date  
        
**Grupa**

    - id  
    - devices
    - administrator
    
