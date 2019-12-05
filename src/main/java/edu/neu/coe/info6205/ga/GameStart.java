package edu.neu.coe.info6205.ga;

import edu.neu.coe.info6205.life.base.Game;
import edu.neu.coe.info6205.life.base.Point;
import edu.neu.coe.info6205.mymatrix.ConsoleMatrixWriter;
import edu.neu.coe.info6205.mymatrix.MatrixWriter;
import edu.neu.coe.info6205.mymatrix.MyMatrix;
import io.jenetics.BitGene;
import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import io.jenetics.internal.util.bit;
import io.jenetics.util.ISeq;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 **/
@Log4j2
public class GameStart {

    private static Map<String, Long> saveGenerationCount = new HashMap<>();
    private static HashMap<String, String> saveInitPattern = InitPattern.getPattern();
    static MatrixWriter writer = new ConsoleMatrixWriter();

    public static void main(String[] args) {
        int count = 0;
        saveInitPattern.forEach((a, b) -> {
            Game.Behavior run = Game.run(0L, b);
            long generation = run.generation;
            saveGenerationCount.put(a, generation);
            saveInitPattern.put(a, b);
        });
        List<String> topPattern = selectTopPattern();
        String s = saveInitPattern.get(topPattern.get(0));
        boolean fitness = BinaryGa.fitness(s);
        while (!fitness) {
            String chromosomes = topPattern.get(0);
            StringBuilder builder = new StringBuilder();
            StringBuilder line = new StringBuilder();
            List<String> result = Arrays.asList(chromosomes.split(","));
            for (int i = 0; i <result.size(); i++) {
                String value = result.get(i);
                for (int j = 0; j < value.length(); j++) {
                    Character c = value.charAt(j);
                    //mutation
                    c = Mutation.mutate(c);
                    if (c.equals('1')) {
                        line.append(i);
                        line.append(" ");
                        line.append(j);
                        line.append(",");
                    }
                    builder.append(c);
                    if (i == 0 && j == 8) {
                        builder.append(',');
                    } else if (i != 0 && j == 7 && i != 7) {
                        builder.append(',');
                    }
                }
            }
            String stringLine = line.toString();
            Game.Behavior run = Game.run(0L,stringLine );
            long generation = run.generation;
            saveGenerationCount.put(builder.toString(), generation);
            saveInitPattern.put(builder.toString(), stringLine);
            topPattern = selectTopPattern();
            fitness = BinaryGa.fitness(saveInitPattern.get(topPattern.get(0)));
            count++;
        }
//        log.info("get result after {} times", count);
        List<String> result = selectTopPattern();
        String s1 = saveInitPattern.get(result.get(0));
        System.out.println(s1);
        List<Point> points = Point.points(s1);
        MyMatrix myMatrix = new MyMatrix(10, 10);
        points.forEach(point -> myMatrix.activateCell(point.getX(), point.getY()));
        System.out.println(points);
        writer.write(myMatrix.getCells());
        MyMatrix next = myMatrix.judgeNextGeneration();
        next = next.judgeNextGeneration();
        System.out.println("=========first grade=============");
        writer.write(next.getCells());
        System.out.println("=========second grade=============");
        next = next.judgeNextGeneration();
        writer.write(next.getCells());
        System.out.println("=========third grade=============");
        next = next.judgeNextGeneration();
        writer.write(next.getCells());
        System.out.println("=========four grade=============");
        next = next.judgeNextGeneration();
        writer.write(next.getCells());
        System.out.println("=========five grade=============");
        next = next.judgeNextGeneration();
        writer.write(next.getCells());
    }

    /**
     * get top 1 pattern
     *
     * @return
     */
    private static List<String> selectTopPattern() {
        List<Map.Entry<String, Long>> list = new ArrayList<>(saveGenerationCount.entrySet());
        list.sort((o1, o2) -> (int) (o2.getValue() - o1.getValue()));
        list.forEach(a -> System.out.println(a.getValue() + "  " + a.getKey()));
        List<Map.Entry<String, Long>> subList = list.subList(0, 1);
        return subList.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
