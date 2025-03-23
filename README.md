# JBPetTracker
Aplikacija za pracenje pasa koriscenjem JBPetTracker uredjaja.  
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
- Doda uredjaj
- Izmeni uredjaj
- Prati lokaciju svih svojih uredjaja
    - lokacija se cuva onog trenutka kada korisnik pokrene sesiju
    - svaki uredjaj ima listu lokacija organizovanih po datumu  
    - korisnik moze da pristupi listi lokacija uredjaja po deviceId + date  
- Kreira grupu
    - korisnik koji kreira grupu, automatski postaje njen administrator  
    - grupa ce prikazivati listu uredjaja sa njihovim lokacijama  
    - uredjaj moze da se doda i izbaci iz grupe samo od strane administratora   
    - dodavanjem novog korisnika, dodaju se i svi njegovi uredjaji  
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

**Opis**  

- Korisnik ima listu uredjaja.  
- Uredjaj ima listu lokacija.  
        
        User {
            id  
            username  
            password  
            devices {
                Device {
                    Location {
                        coordinates
                        timestamp
                    }
                }
            }
        }

        Group {
            id
            users
            administrator
        }

**DeviceTrackingSession**  

- Svaka sesija bi mogla da bude grupa zbog lakse logike.  
- Prilikom prikazivanja mape, moguce je kreirati sesiju.  
- U sesiju je moguce dodati celu grupu, ili pojedinca.  
- Prilikom dodavanja, drugi klijent mora da prihvati poziv.  

        DeviceTrackingSession {
            id
            deviceId
            deviceName
            date
            locations
        }
    
