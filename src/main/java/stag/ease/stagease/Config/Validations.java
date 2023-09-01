package stag.ease.stagease.Config;

import org.springframework.stereotype.Component;

@Component
public class Validations {
    public static boolean validaNome(String nome) {
        String nomeInserido = "^[a-zA-Z ]+$";
        return nome.matches(nomeInserido);
    }
    public static String formataNome(String nome) {
        nome = nome.toLowerCase();
        nome = Character.toLowerCase(nome.charAt(0)) + nome.substring(1);
        return nome;
    }
    public static boolean validaTelefone(String tel) {
        String telBR = "[1-9][0-9] [2-9]{2}[0-9]{3}[0-9]{4}";
        return tel.matches(telBR);
    }

    // NECESSÁRIO REALIZAR AINDA A VALIDAÇÃO DE DATAS E HORÁRIOS
}
