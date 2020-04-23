import React, { Component } from "react";
import "./Contact.css";
class Contact extends Component {
  render() {
    return (
      <div className="contact">
        <h1 className="titlu">Contacteaza-ne</h1>
        <p className="textIntroducere">
          Suntem la dispozitia ta gata sa te ajutam indiferent de ce problama ai
          intalnit. Pentru a beneficia de suportul nostru, te incurajam sa ne
          contactezi folosind una din variantele de mai jos:
        </p>
        <div class="row">
          <div class="column-left">
            <h3 className="adresaTitlu">Adresa</h3>
            <p className="adresaText">
              Strada Subcetate nr. 39, Cartier ANL
              <br />
              Loc. Bistrita, jud. Bistrita-Nasaud,
              <br />
              Cod Postal:420132, Romania
            </p>
          </div>
          <div class="column-right">
            <h3 className="titluContact">Contact</h3>
            <p className="text-adresa">
              <strong>Telefon (mobil):</strong> 0725365880
              <br />
              <strong>Telefon (fix):</strong> 0263238127
              <br />
              <strong>Email:</strong> jonathan_patrascu@yahoo.com
              <br />
              <strong>Facebook:</strong>{" "}
              <a
                className="facebook-referinta"
                href="https://www.facebook.com/jonathan.jp.73"
                target="Facebook"
              >
                Jonathan Patrascu `
              </a>
            </p>
          </div>
        </div>
      </div>
    );
  }
}

export default Contact;
