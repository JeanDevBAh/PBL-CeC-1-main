package model;

/**
 * Enum das cidades disponiveis que serão vertices do grafo a ser criado
 * O tahiti é proibido de ser acessado 
 */
public enum Cidades {//cidades baseadas no jogo RDR2
    VALENTINE("Valentine", true),
    RHODES("Rhodes", true),
    SAINT_DENIS("Saint Denis", true),
    STRAWBERRY("Strawberry", true),
    BLACKWATER("Blackwater", true),
    ANESBURG("Anesburg", true),
    VANHORN("Vanhorn", true),
    EMERALD_RANCH("Emerald Ranch", true),
    CORNWALL_KEROSENE("Cornwall Kerosene", true),
    BUTCHER_CREEK("Butcher Creek", true),
    LAGRAS("Lagras", true),
    BRAITHWAITE("Braithwaite", true),
    CALIGA_HALL("Caliga Hall", true),
    MANZANITA_POST("Manzanita Post", true),
    COLTER("Colter", true),
    WAPITI("Wapiti", true),
    ARMADILLO("Armadillo", true),
    TUMBLEWEED("Tumbleweed", true),
    MACFARLANES_RANCH("Macfarlanes Ranch", true),
    THIEVES_LANDING("Thieves Landing", true),
    PLAINVIEW("Plainview", true),
    TAHITI("Tahiti", false);

    private final String nome;
    private final boolean disponivel;

    Cidades(String nome, boolean disponivel) {
        this.nome = nome;
        this.disponivel = disponivel;
    }

    public String getNome() {
        return nome;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    @Override
    public String toString() {
        return nome;
    }
    
}
