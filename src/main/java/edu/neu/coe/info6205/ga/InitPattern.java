package edu.neu.coe.info6205.ga;

import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;

/**
 * @author
 **/
@Log4j2
public class InitPattern {
    public static void main(String[] args) {
        HashMap<String, String> pattern = getPattern();
        System.out.println(pattern);
    }
    public static HashMap<String, String> getPattern() {
        HashMap<String, String> startPointList = new HashMap<>();
        int count = 0;
        while (count < 8) {
            int numberOfPoint = 8;
            Genotype<BitGene> gtf
                    = Genotype.of(BitChromosome.of(8, 0.05), numberOfPoint);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < gtf.length(); i++) {
                Chromosome<BitGene> bitGenes = gtf.get(i);
                String bit = bitGenes.toString();
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < bit.length(); j++) {
                    Character c = bit.charAt(j);
                    if (c.equals('1')) {
                        line.append(i);
                        line.append(" ");
                        line.append(j);
                        line.append(",");
                    }
                }
                stringBuilder.append(line);
            }
            if (stringBuilder.toString().length() > 0) {
                String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
                startPointList.put(gtf.toString(), substring);
            }
            count++;
        }
        return startPointList;
    }
}
