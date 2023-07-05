package model.beans;

public class WishlistBean 
{
    private int id_wishlist;
	private String id_prodotto;
	private String utente;
	
	public WishlistBean() 
	{
        id_wishlist = 0;
		id_prodotto = "";
		utente = "";
	}

	public int getIdWishlist() {
		return id_wishlist;
	}

	public void setIdWishlist(int id_wishlist) {
		this.id_wishlist = id_wishlist;
	}    

	public String getIdProdotto() {
		return id_prodotto;
	}

	public void setIdProdotto(String id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

}
