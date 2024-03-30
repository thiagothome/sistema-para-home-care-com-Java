package br.com.iffar.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import br.com.iffar.model.RelatorioView;

@ManagedBean
@ViewScoped
public class RelatorioController {

	@PostConstruct
	public void init() {

	}

	public void mostrarRelatorio() {
		List<RelatorioView> dados = obterDadosRelatorio();

		try {
			String outputPath = "C:\\Users\\adrit\\Downloads\\relatorio.pdf";
			PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputPath));
			Document document = new Document(pdfDocument);

			document.add(new Paragraph("Relatório de Cuidados"));

			document.add(new Paragraph());

			document.add(new Paragraph("Anotação\tData Atendimento\tNome Colaborador"));

			for (RelatorioView relatorio : dados) {
				String rowData = relatorio.getAnotacao() + "\t" + relatorio.getData_atendimento().toString() + "\t"
						+ relatorio.getNome_colaborador();
				document.add(new Paragraph(rowData));

				document.add(new Paragraph());
			}

			document.close();
			FacesContext.getCurrentInstance().addMessage("formRelatorio:msgsRelatorio",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Relatório gerado com sucesso!", null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<RelatorioView> obterDadosRelatorio() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sistemahomecare");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		System.out.println("obter relatorio");

		try {
			String jpqlQuery = "SELECT r FROM RelatorioView r";
			TypedQuery<RelatorioView> query = entityManager.createQuery(jpqlQuery, RelatorioView.class);
			return query.getResultList();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}
}
