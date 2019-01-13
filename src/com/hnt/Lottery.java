package com.hnt;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Lottery {

  private Map<Integer, String> nameMap;
  private Integer[] array;
  private int count = 0;

  public void drawPrize(String range) {
    count = Integer.parseInt(range);
    array = new Integer[count];
    for (int i = 0; i < array.length; i++) {
      array[i] = i+1;
    }
    Collections.shuffle(Arrays.asList(array));
  }

  public String revealPrize() {
    String result = "";
    if (count > 0) {
      result = array[count-1].toString() + " " + nameMap.get(array[count-1]) + "\n";
      count--;
    }
    return result;
  }

  public void getNameFromFile() {
    String fileName = "prize.txt";
    nameMap = new HashMap<>();

    int numbering = count;
    try (Scanner scanner = new Scanner(new File(fileName))) {
      while (scanner.hasNext()) {
        nameMap.put(numbering, scanner.nextLine());
        numbering--;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
