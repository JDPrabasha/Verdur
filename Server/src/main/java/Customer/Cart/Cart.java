package Customer.Cart;

public class Cart {
    private int id;
    private int apple;
    private int orange;
    private int lemon;
    private int lime;

    public Cart(int apple, int orange, int lemon, int lime) {

        this.apple = apple;
        this.orange = orange;
        this.lemon = lemon;
        this.lime = lime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApple() {
        return apple;
    }

    public void setApple(int apple) {
        this.apple = apple;
    }

    public int getOrange() {
        return orange;
    }

    public void setOrange(int orange) {
        this.orange = orange;
    }

    public int getLemon() {
        return lemon;
    }

    public void setLemon(int lemon) {
        this.lemon = lemon;
    }

    public int getLime() {
        return lime;
    }

    public void setLime(int lime) {
        this.lime = lime;
    }


}

