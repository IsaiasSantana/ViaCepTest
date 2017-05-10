package br.com.viaceptest.domain;

/**
 * @author isaias
 * @since 09/05/17.
 */

public class Endereco
{
    private String bairro;
    private String cep;
    private String logradouro;
    private String localidade; // cidade
    private String uf;
    private String complemento;

    public static final int RESQUEST_ZIP_CODE_CODE = 1994;
    public static final String ZIP_CODE_KEY = "zip_code_key";

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
