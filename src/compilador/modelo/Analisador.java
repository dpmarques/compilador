/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.modelo;

/**
 *
 * @author kigs
 */
public class Analisador {
    
    
   public String Saida;
    public String mensagemErro;
    private int l;
    int ver = 0;
    String[] Token = new String[53];
    
    
        public boolean Letras(char texto) {
        if ('a' == texto || 'b' == texto || 'c' == texto || 'd' == texto || 'e' == texto
                || 'f' == texto || 'g' == texto || 'h' == texto || 'i' == texto || 'j' == texto
                || 'k' == texto || 'l' == texto || 'm' == texto || 'n' == texto || 'o' == texto
                || 'p' == texto || 'q' == texto || 'r' == texto || 's' == texto || 't' == texto
                || 'u' == texto || 'v' == texto || 'w' == texto || 'x' == texto || 'y' == texto
                || 'z' == texto || 'ç' == texto) {
            return true;
        }
        return false;
    }
        
      public void Compilar(Principal janela) {

        String texto = "";
        int j;
        int ct;
        int cont = 0;
        char[] Tudo = janela.jTextArea1.getText().toLowerCase().toCharArray();
        char[] Auxiliar = janela.jTextArea1.getText().toLowerCase().toCharArray();
        mensagemErro = "";
        Saida = "";
        ver = 0;
        janela.jTextArea2.setText("");
        janela.jTextArea3.setText("");
        l = 1;
        int l1;
        int verificaTamanho = Tudo.length;

        for (j = 0; j < verificaTamanho; j++) {
            if (Tudo[j] == ' ') {
                Auxiliar = null;
            } else {
                Auxiliar = janela.jTextArea1.getText().toCharArray();
                break;
            }
        }

        Token();

        if (Auxiliar == null || verificaTamanho < 1) {
            mensagemErro += ("Não existe código a ser Compilado!");
            janela.jTextArea3.setText(mensagemErro);
        } else {
            for (int i = 0; i < verificaTamanho; i++) {
                if (ver == 1) {
                    Tudo = null;
                }
                if (Tudo == null) {
                    break;
                } else {
                    if (Tudo[i] == '\n') {
                        l++;
                    }
                    if (Tudo[i] == ' ') {
                        texto = "";
                        continue;
                    }

                    if (Tudo[i] == ',' || Tudo[i] == ';' || Tudo[i] == '.') {
                        texto += Tudo[i];
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    }

                    //comentario de linha
                    if (Tudo.length > 1) { // Para nao gerar ArrayIndexOutOfBoundsException
                        if (Tudo[i] == '/' && Tudo[i + 1] == '/') {
                            l1 = l;
                            for (j = i + 1; j < Tudo.length; j++) {
                                if (Tudo[j] == '\n') {
                                    l++;
                                    break;
                                }
                            }
                            Saida += " \n--- Comentário de Linha--- " + "\n Linha: " + l1 ;

                            janela.jTextArea2.setText(Saida);
                            i = j - 1;
                            j = l--;
                            continue;
                        }
                        //comentario de bloco
                        if (Tudo[i] == '/' && Tudo[i + 1] == '?') {
                            l1 = l;
                            for (j = i + 1; j < Tudo.length; j++) {
                                if (Tudo[j] == '\n') {
                                    l++;
                                }
                                if (Tudo[j-1] == '?' && Tudo[j] == '/') {
                                    Saida += "\n---Comentário de Bloco---" + "\n Linha: " + l1 + " até " + l;
                                    janela.jTextArea2.setText(Saida);
                                    break;
                                }
                                if (j == verificaTamanho - 1 || j == verificaTamanho) {
                                    cont = 1;
                                    ver = 1;
                                    mensagemErro += ("\n Não foi Encontrado '?/' para finalizar o comentario de bloco! Linha: " + l);
                                    janela.jTextArea3.setText(mensagemErro);
                                    break;
                                }
                            }
                            i = j;
                            texto = "";
                            continue;
                            //break; //para nao pegar outros caracteres se for comentario de bloco
                        }
                    }
                    // Literal
                    if (Tudo[i] == '"') {
                        l1 = l;
                        for (j = i + 1; j < Tudo.length; j++) {
                            if (Tudo[j] == '\n') {
                                l++;
                            }
                            if (Tudo[j] == '"') {
                                Saida += "\n Linha: " + l1 + " até " + l + " Codigo: 9 - Literal - Mensagem literal: " + texto.trim() + " \n";
                                janela.jTextArea2.setText(Saida);
                                break;
                            }
                            //texto += "" + Tudo[j];
                            if (j == verificaTamanho - 1) {
                                cont = 1;
                                ver = 1;
                                mensagemErro += ("\nNão foi Encontrado 'aspas dupla' para finalização do literal! Linha: " + l);
                                janela.jTextArea3.setText(mensagemErro);
                                break;
                            }
                        }
                        i = j;
                        texto = "";
                        continue;
                    }
                    if (Tudo[i] == '/') {
                        texto += Tudo[i];
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    }
                    if (Tudo[i] == '*') {
                        texto += Tudo[i];
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    }
                    if (Tudo[i] == '+') {
                        texto += "" + Tudo[i];
                        for (j = i + 1; j < Tudo.length; j++) {
                            if (Tudo[j] == '+') {
                                texto += "" + Tudo[j];
                                i = j;
                                break;
                            } else {
                                i = j - 1;
                                break;
                            }
                        }
                        janela.jTextArea2.setText(Saida);
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    }
                    if (Tudo[i] == '-') {
                        texto += "" + Tudo[i];
                        for (j = i + 1; j < Tudo.length; j++) {
                            if (Tudo[j] == '-') {
                                texto += "" + Tudo[j];
                                i = j;
                                break;
                            } else {
                                i = j - 1;
                                break;
                            }
                        }
                        janela.jTextArea2.setText(Saida);
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    }

                    if (Tudo[i] == '(' || Tudo[i] == ')') {
                        texto += Tudo[i];
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    }
                    if (Tudo[i] == '=') {
                        texto += "" + Tudo[i];
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    }
                    if (Tudo[i] == '<') {
                        texto += "" + Tudo[i];
                        for (j = i + 1; j < Tudo.length; j++) {
                            if (Tudo[j] == '=' || Tudo[j] == '>') {
                                texto += "" + Tudo[j];
                                i = j;
                                break;
                            } else {
                                i = j - 1;
                                break;
                            }
                        }
                        Dicionario(janela, texto);
                        texto = "";
                        continue;
                    } else {
                        if (Tudo[i] == '>') {
                            texto += "" + Tudo[i];
                            for (j = i + 1; j < Tudo.length; j++) {
                                if (Tudo[j] == '>') {
                                    texto += "" + Tudo[j];
                                    i = j;
                                    break;
                                } else {
                                    i = j - 1;
                                    break;
                                }
                            }
                            janela.jTextArea2.setText(Saida);
                            Dicionario(janela, texto);
                            texto = "";
                            continue;
                        } else {
                            if (Tudo[i] == ':') {
                                texto += "" + Tudo[i];
                                for (j = i + 1; j < Tudo.length; j++) {
                                    if (Tudo[j] == '=') {
                                        texto += "" + Tudo[j];
                                        i = j;
                                        break;
                                    } else {
                                        i = j - 1;
                                        break;
                                    }
                                }
                                Dicionario(janela, texto);
                                texto = "";
                                continue;
                            }
                        }
                    }
                    if (Letras(Tudo[i]) == true) {
                        texto += "" + Tudo[i];
                        for (j = i + 1; j < Tudo.length; j++) {
                            if (Tudo[j] == ' ' || Tudo[j] == '\n' || Tudo[j] == '[' || Tudo[j] == ']' || Tudo[j] == ';'
                                    || Tudo[j] == '|' || Tudo[j] == '('
                                    || Tudo[j] == ')' || Tudo[j] == '=' || Tudo[j] == '>' || Tudo[j] == '<'
                                    || Tudo[j] == ':' || Tudo[j] == '.' || Tudo[j] == ',' || Tudo[j] == '$'
                                    || Tudo[j] == '&' || Tudo[j] == '!'
                                    || Tudo[j] == '¨' || Tudo[j] == '"' || Tudo[j] == '{' || Tudo[j] == '}') {

                                break;
                            }
                            if (Letras(Tudo[j]) == true) {
                                texto += "" + Tudo[j];
                            } else {
                                if (Numero(Tudo[j]) == true) {
                                    texto += "" + Tudo[j];
                                } else {
                                    break;
                                }
                            }
                        }
                        if (texto.length() > 30) {
                            mensagemErro += ("Erro ao Compilar. Tamanho do identificador maior que o permitido '" + texto.trim() + "Linha: " + l);
                            janela.jTextArea3.setText(mensagemErro);
                            cont = 1;
                            ver = 1;
                            continue;
                        }
                        if (cont == 0) {
                            if (Dicionario(janela, texto) == true) {
                            } else {
                                Saida += "\n Linha: " + l + " Codigo: 14 - Ident Valor: " + texto.trim();
                                janela.jTextArea2.setText(Saida);
                            }
                        }
                        texto = "";
                        i = j - 1;
                        continue;
                    }
                    if (Numero(Tudo[i]) == true) {
                        texto += "" + Tudo[i];
                        for (j = i + 1; j < Tudo.length; j++) {
                            if (Letras(Tudo[j]) == true || Tudo[j] == '[' || Tudo[j] == ']' || Tudo[j] == '%'
                                    || Tudo[j] == '#' || Tudo[j] == '&' || Tudo[j] == '¨' || Tudo[j] == '"'
                                    || Tudo[j] == '{' || Tudo[j] == '}') {
                                cont = 1;
                                ver = 1;
                                mensagemErro += ("Não pode começar variavel com numero! Linha do erro: " + l);
                                janela.jTextArea3.setText(mensagemErro);

                                break;
                            }
                            if (Tudo[j] == ' ' || Tudo[j] == '|' || Tudo[j] == ';'
                                    || Tudo[j] == '(' || Tudo[j] == ')' || Tudo[j] == '=' || Tudo[j] == '>'
                                    || Tudo[j] == '<' || Tudo[j] == ':' || Tudo[j] == '|' || Tudo[j] == ','
                                    || Tudo[j] == '$' || Tudo[j] == '!') {
                                break;
                            }
                            if (Numero(Tudo[j]) == true) {
                                texto += "" + Tudo[j];
                            } else {
                                break;
                            }
                        }
                        int k = Integer.parseInt(texto.trim());
                        if (k > 2500000) {
                            mensagemErro += ("Caracter maior que o permitido: " + texto.trim() + "Linha: " + l);
                            janela.jTextArea3.setText(mensagemErro);

                            cont = 1;
                            ver = 1;
                            continue;
                        }

                        if (cont == 0) {
                            Saida += "\n Linha: " + l + " Codigo: 8 - Inteiro Valor: " + texto.trim() + "\n";
                            janela.jTextArea2.setText(Saida);
                            texto = "";
                        }
                        cont = 0;
                        i = j - 1;
                    }

                }
            }
        }
    }
      public Analisador() {
    }

    public void Token() {
//terminais
        Token[1] = "while";
        Token[2] = "switch";
        Token[3] = "%"; //string
        Token[4] = "or";
        Token[5] = "numerointeiro";
        Token[6] = "numerofloat";
        Token[7] = "nomestring";
        Token[8] = "nao";
        Token[9] = "literal";
        Token[10] = "int";
        Token[11] = "inicioprograma";
        Token[12] = "if";
        Token[13] = "ident";
        Token[14] = "î";
        Token[15] = "funcao";
        Token[16] = "for";
        Token[17] = "float";
        Token[18] = "end";
        Token[19] = "else";
        Token[20] = "default";
        Token[21] = "cout";
        Token[22] = "cin";
        Token[23] = "chama";
        Token[24] = "case";
        Token[25] = "break";
        Token[26] = "begin";
        Token[27] = "and";
        Token[28] = ">>";
        Token[29] = ">=";
        Token[30] = ">";
        Token[31] = "==";
        Token[32] = "=";
        Token[33] = "<>";
        Token[34] = "<=";
        Token[35] = "<<";
        Token[36] = "<";
        Token[37] = "++";
        Token[38] = "+";
        Token[39] = "}";
        Token[40] = "{";
        Token[41] = ";";
        Token[42] = ":";
        Token[43] = "/";
        Token[44] = ".";
        Token[45] = ",";
        Token[46] = "*";
        Token[47] = ")";
        Token[48] = "(";
        Token[49] = "$";
        Token[50] = "!=";
        Token[51] = "--";
        Token[52] = "-";
        //Token[53] = "//";
        //Token[54] = "//?";
    }

    public boolean Dicionario(Principal janela, String texto) {

        boolean verificar = false;

        for (int i = 1; i < Token.length; i++) {
            //teste de linha
            //janela.txtSaida.setText("linha teste lida com sucesso!");

            if (Token[i].equals(texto)) {
                if (i == 1) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 1 Valor:  while";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 2) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 2  Valor:  switch";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 3) {
                    Saida += "\nPalavra Reservada  Linha: 3 Codigo: " + i + " Valor: string";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 4) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 4 Valor: or";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 5) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 5 Valor: numerointeiro";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 6) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 6 Valor: numerofloat";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 7) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 7 Valor:  nomestring";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 8) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 8 Valor:  nao";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 9) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 9 Valor:  literal";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 10) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 10 Valor: int";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 11) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: " + i + "11 Valor:  inicioprograma";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 12) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 12 Valor:  if";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 13) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 13 Valor: ident";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 14) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 14 Valor: î";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 15) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 15 Valor: funcao";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 16) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 16 Valor: for";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 17) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 17 Valor: float";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 18) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 18 Valor:  end";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 19) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 19 Valor:  else";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 20) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 20 Valor: default";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 21) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 21 Valor: cout";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 22) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 22 Valor: cin";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 23) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 23 Valor:  chama";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 24) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 24 Valor:  case";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 25) {
                    Saida += "\nPalavra Reservada  Linha: " + l + " Codigo: 25 Valor:  break";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 26) {
                    Saida += "\nPalavra reservada  Linha: " + l + " Codigo: 26 Valor:  begin";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 27) {
                    Saida += "\nPalavra reservada  Linha: " + l + " Codigo: 27 Valor:  and";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;

                } else if (i == 28) {
                    Saida += "\nComparador Terminal  Linha: " + l + " Codigo: 28 Valor:  >>";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;

                } else if ((i == 29) || (i == 30) || (i == 31) || (i == 32) || (i == 33) || (i == 34)
                        || (i == 35) || (i == 36) || (i == 50)) {
                    Saida += "\nOperador logico Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if ((i == 38) || (i == 43) || (i == 44) || (i == 46) || (i == 52)) {
                    Saida += "\nOperador Matemático Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if ((i == 39) || (i == 40)) {
                    Saida += "\n   Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;

                } else if (i == 42) {
                    Saida += "\n Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 45) {
                    Saida += "\n Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 37) {
                    Saida += "\n Incremento  Linha: " + l + " Codigo: 37 Valor:  ++";
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 41) {
                    Saida += "\n  Linha: " + l + " Codigo: 41 Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 47) {
                    Saida += "\n Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 48) {
                    Saida += "\n Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 49) {
                    Saida += "\n Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                } else if (i == 51) {
                    Saida += "\n Decremento Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
                    janela.jTextArea2.setText(Saida);
                    verificar = true;
                    break;
                }
//                else if (i == 53) {
//                    Saida += "\nComentario de linha Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
//                    janela.txtSaida.setText(Saida);
//                    verificar = true;
//                    break;
//                }
//                 else if (i == 54) {
//                    Saida += "\nComentario de bloco Linha: " + l + " Codigo: " + i + " Valor:  " + texto.trim();
//                    janela.txtSaida.setText(Saida);
//                    verificar = true;
//                    break;
//                }
            }
            janela.jTextArea2.setText("linha 558 lida com sucesso!");
        }

        return verificar;
        //return false;
    }

    public boolean Numero(char texto) {
        if (texto == '0' || texto == '1' || texto == '2' || texto == '3' || texto == '4'
                || texto == '5' || texto == '6' || texto == '7' || texto == '8' || texto == '9') {
            return true;
        }
        return false;
    }

    public boolean VerificaInt(Principal janela, String texto, int l) {
        if (texto.length() > 30) {
            mensagemErro += ("Erro ao Compilar. Tamanho do identificador maior que o permitido '" + texto.trim() + "Linha: " + l);
            janela.jTextArea3.setText(mensagemErro);
            
            return true;
        }
        return false;
    }
}

