package br.com.fiap.analitics.Domain;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AllTransaction {
    private double maximum;
    private double minimum;
    private long counter;
    private double sum;
    private double avger;
}