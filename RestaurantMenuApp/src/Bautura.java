
public final class Bautura extends Produs {
    private int volume; // în mililitri
    private boolean isAlcoholic;

    public Bautura(String name, double price, int volume, boolean isAlcoholic) {
        super(name, price);
        this.volume = volume;
        this.isAlcoholic = isAlcoholic;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    @Override
    public String getSpecificDetails() {
        return String.format("Volum: %dml", volume);
    }
}