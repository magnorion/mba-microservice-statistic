package br.com.fiap.analitics.Domain;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AllTransaction {
    private double sum;
    private double min;
    private double max;
    private double avg;
    private long count;
}