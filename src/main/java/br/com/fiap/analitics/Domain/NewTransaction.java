package br.com.fiap.analitics.Domain;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NewTransaction {
    private double amount;
    private long data;
    private long timestamp;
}
