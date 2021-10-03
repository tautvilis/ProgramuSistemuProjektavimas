# ProgramuSistemuProjektavimas

## Ar buvo aiškus ir patogus unit testai, ar kodas aiškus.
Testai nebuvo labai patogus, kadangi visi testai buvo tikrinami su viena funkcija iš vieno Validator failo. Pvz.: visi mobialus numerio testai, buvo tikrinami su funkcija phoneChecker, el.pašto tikrinami su emailChecker ir t.t. Ko pasekoje, leidžiant testus nebūtų galima patikrinti klaidos, nes galimai jis praeina vienus testus, tačiau specifinio pasirinkto ne.
	
## Kaip jus galėtumete juos pagerinti
Kiekvienam testui naudočiau atskirus Validator klases, taip pat išskirčiau atskira funkcija kiekvienam testui, taip būtų galima gauti klaidą, kurio jis nepraėjo.
	
## Kokius unit testus jus galėtumėte pridėti (jei tokių yra)
Mobilaus numerio testams pridėčiau tikrinima pagal prefix ir šalį. 
