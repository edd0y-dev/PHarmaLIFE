package controller.utente;

import model.prodotto.Prodotto;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletRimuoviPreferito", value = "/ServletRimuoviPreferito")
public class ServletRimuoviPreferito extends HttpServlet {
    private UtenteDAOMethod utenteDAO; //prima era UtenteDAOMethod

    public ServletRimuoviPreferito(UtenteDAO utenteDAO){
        this.utenteDAO = utenteDAO;
    }
    public ServletRimuoviPreferito(){
        utenteDAO = new UtenteDAO();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        rimuoviProdottoDaiPreferiti(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * @pre //
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @post service.doRetrieveByAllPreferitiOfUtente.size=@pre.service.doRetrieveByAllPreferitiOfUtente.size-1
     */
    public void rimuoviProdottoDaiPreferiti(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        if(session != null) {
            //utenteDAO = new UtenteDAO();
            Utente utente = (Utente) session.getAttribute("utente");
            if (utente != null) {
                Prodotto p=new Prodotto();
                p.setCodiceProdotto(codiceProdotto);
                utenteDAO.deletePreferito(utente,p);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }
    }
}
