package aed;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private int patrimonio;

    public Usuario(int id) {
        this.id = id;
        this.patrimonio = 0;
    }


    @Override
    public int compareTo(Usuario otro) {
        // Compara patrimonio
        if (this.patrimonio > otro.patrimonio) {
            return 1;
        } else if (this.patrimonio < otro.patrimonio) {
            return -1;
        }
        // Si el patrimonio es igual, compara ID en orden descendente
        if (this.id < otro.id) {
            return 1;
        } else if (this.id > otro.id) {
            return -1;
        }
        // Son exactamente el mismo usuario (mismo id y patrimonio)
        return 0;
    }
    
    @Override
    public boolean equals(Object otro){
        boolean otraEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();

        if (otraEsNull || claseDistinta){
            return false;
        }

        Usuario otraUsuario = (Usuario) otro;

	    return this.patrimonio == otraUsuario.patrimonio && this.id == otraUsuario.id;    
    }

    public int patrimonio() {
        return patrimonio;
    }

    public int id() {
        return id;
    }

    public void sumar(int monto) {
        patrimonio += monto;
    }
    
}