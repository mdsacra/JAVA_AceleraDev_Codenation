package challenge;

public class CriptografiaCesariana implements Criptografia {

    private void validarTexto(String texto) {
        if (texto == null) {
            throw new NullPointerException();
        } else if (texto.trim().equals("")) {
            throw new IllegalArgumentException();
        }
    }

    private String encdesc(String texto, int casas) {

        StringBuilder cripto = new StringBuilder();
        texto = texto.toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) < 97 || texto.charAt(i) > 122){
                char caraEsp = texto.charAt(i);
                cripto.append(caraEsp);
            } else {
                int caraCrip = (int) texto.charAt(i) + (casas);
                if (casas > 0 && caraCrip > 122) {
                    caraCrip -= 23;
                } else if (casas < 0 && caraCrip < 97) {
                    caraCrip += 23;
                }
                char novaLetra = (char) caraCrip;
                cripto.append(novaLetra);
            }
        }
        return cripto.toString();
    }

    @Override
    public String criptografar(String texto) {

        validarTexto(texto);
        return encdesc(texto, 3);
    }

    @Override
    public String descriptografar(String texto) {

        validarTexto(texto);
        return encdesc(texto, -3);
    }
}
