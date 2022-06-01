public class Results {
    private String docsName1;
    private String docsName2;
    private double docsSimilarity;

    public Results(String docsName1, String docsName2, double docsSimilarity) {
        this.docsName1 = docsName1;
        this.docsName2 = docsName2;
        this.docsSimilarity = docsSimilarity;
    }


    public double getDocsSimilarity() {
        return docsSimilarity;
    }

    public void setDocsSimilarity(double docsSimilarity) {
        this.docsSimilarity = docsSimilarity;
    }


    public String getDocsName1() {
        return docsName1;
    }

    public void setDocsName1(String docsName1) {
        this.docsName1 = docsName1;
    }

    public String getDocsName2() {
        return docsName2;
    }

    public void setDocsName2(String docsName2) {
        this.docsName2 = docsName2;
    }
    @Override
    public String toString() {
        return docsName1+" and "+docsName2+" cosine similarity = "+docsSimilarity;
    }



}
