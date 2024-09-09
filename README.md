# Composite

## Cel zadania

Celem zadania była analiza dostarczonego kodu oraz implementacja metod z interfejsu `Structure` w klasie `Wall`. Metody te to:

- **`Optional<Block> findBlockByColor(String color)`** - zwraca dowolny element o podanym kolorze.
- **`List<Block> findBlocksByMaterial(String material)`** - zwraca wszystkie elementy z danego materiału.
- **`int count()`** - zwraca liczbę wszystkich elementów tworzących strukturę.

## Analiza kodu

- **Interfejs `Block`**  
  Podstawowy element budujący strukturę, tzw. "liść" we wzorcu projektowym Kompozyt.
  
- **Interfejs `CompositeBlock`**  
  Rozszerza interfejs `Block`. Obiekty implementujące ten interfejs zawierają listę obiektów implementujących `Block`, tworząc "kompozyt" według wzorca projektowego Kompozyt.
  
- **Klasa `Wall`**  
  Implementuje interfejs `Structure` i składa się z listy elementów implementujących interfejs `Block`. Tymi elementami mogą być zarówno "liście", jak i "kompozyty".
  
- **Interfejs `Structure`**  
  Zawiera metody związane z przeszukiwaniem i badaniem elementów struktury, takich jak szukanie elementu po kolorze czy zliczanie liczby elementów.
