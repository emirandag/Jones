/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Objects;

/**
 *
 * @author iaw26540084
 */
public class Account {
    
    private String iban;
    private long saldo;
    private Cliente cliente;
    private String dni;

    public Account() {
    }

    public Account(String iban, long saldo, Cliente cliente, String dni) {
        this.iban = iban;
        this.saldo = saldo;
        this.cliente = cliente;
        this.dni = dni;
    }
    
    public Account(String iban, long saldo, String dni) {
        this.iban = iban;
        this.saldo = saldo;
        this.dni = dni;
    }
    
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.iban);
        hash = 47 * hash + (int) (this.saldo ^ (this.saldo >>> 32));
        hash = 47 * hash + Objects.hashCode(this.cliente);
        hash = 47 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.saldo != other.saldo) {
            return false;
        }
        if (!Objects.equals(this.iban, other.iban)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account{" + "iban=" + iban + ", saldo=" + saldo + ", cliente=" + cliente + ", dni=" + dni + '}';
    }

    

    
    
    
}
