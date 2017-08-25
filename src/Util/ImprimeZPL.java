package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;
import model.Preco;
import model.Produto;

public class ImprimeZPL {
   
    Produto produtoSelecionado = new Produto();
    Preco precoSelecionado = new Preco();
    
    
    private ArrayList etiqueta = new ArrayList();
 //   private String tipoImpressora;
    
    
    
    public String imprimeEtiquetaZebra(String ImpPLU, String ImpEAN, String Impdesc, String ImpPRECO, int qtdeEtiquetas){
        String retorno = "";
//        //recebe o produto enviado por parametro
//        produtoSelecionado = ImpPLU;
//        produtoSelecionado =ImpEAN;
//        produtoSelecionado = Impdesc;
//        //recebe a marca enviada por parametro
//        precoSelecionado = ImpPRECO;
        
      // tipoImpressora = imp;
        //escreve o nome do arquivo
        String nomeArquivo = "Etiquetas"+File.separator+"ETIQUETA - "+produtoSelecionado.getCodigo()+".txt";
        //cria o arquivo
        File diretorio = new File("Etiquetas");
        diretorio.mkdir();
        File arquivoEtiqueta = new File(nomeArquivo);
        //preenche a etiqueta com os dados do produto
        preencheEtiqueta(ImpPLU,ImpEAN,Impdesc,ImpPRECO,qtdeEtiquetas);
        //escreve o conteúdo do arquivo
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoEtiqueta));
            for (int i = 0; i < etiqueta.size(); i++){
                bw.write(etiqueta.get(i).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //instancia um stream para carregamento do arquivo
        FileInputStream dadosEtiqueta = null;
        try {
//            for (int i = 0; i < qtdeLinha; i++) {
                //carrega os dados do arquivo de etiqueta
                dadosEtiqueta = new FileInputStream(arquivoEtiqueta);
                //determina o tipo a ser impresso (txt)
                DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE; 
                SimpleDoc documentoTexto = new SimpleDoc(dadosEtiqueta, docFlavor, null); 
                //verifica quais impressoras sao compativeis com txt
                PrintService impressora = PrintServiceLookup.lookupDefaultPrintService(); 
                //cria a tarefa de impressao
                DocPrintJob printJob = impressora.createPrintJob(); 
                //tenta imprimir
                printJob.print(documentoTexto, null); //(PrintRequestAttributeSet)printerAttributes);
                //fecha o arquivo
                dadosEtiqueta.close();
//            }
        //se nao achar o arquivo    
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        //se nao conseguir imprimir
        } catch(PrintException e){ 
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a impressão !!", "Erro", JOptionPane.ERROR_MESSAGE); 
        //se nao conseguir fechar
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    private void preencheEtiqueta(String ImpPLU,String ImpEAN, String Impdesc, String ImpPRECO, int qtdeEtiquetas){

            String descricao = Impdesc;
            String codigo = ImpPLU;
            String ean = ImpEAN;
            String preco = ImpPRECO;

            
    while(qtdeEtiquetas > 0){
                etiqueta.add("^XA");
                etiqueta.add("^LH0,0^FS");
                etiqueta.add("^LL10,5^FS");
                etiqueta.add("^LT3,0^FS");
                etiqueta.add("^PRD^FS");
                etiqueta.add("~SD20");
                etiqueta.add("^FO40,040^BY2,2.5,70^BER,50,Y,N^FD"+ean+"^FS");
                etiqueta.add("^FO120,30^APN,040,036^FD"+descricao+"^FS");
                etiqueta.add("^FO140,60^AVN,180,142^FD"+"R$"+preco+"^FS");
                etiqueta.add("^FO120,190^APN,040,018,^FD"+"COD/PLU:"+codigo+"^FS");
                etiqueta.add("^XZ");
            qtdeEtiquetas --;
            } 

       }
    
}