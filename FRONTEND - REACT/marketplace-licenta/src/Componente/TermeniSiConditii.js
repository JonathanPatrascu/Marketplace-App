import React, { Component } from "react";
import "./TermeniSiConditii.css";
class TermeniSiConditii extends Component {
  render() {
    return (
      <div className="row">
        <h1 className="titlu">Cum functioneaza?</h1>
        <ul>
          <li>
            Aplicatia Marketplace Echipamente Muzicale nu este conceputa pentru
            a fi utilizata de catre dealeri. Un dealer este orice persoana care
            cumpara si vinde echipamente muzicale in schimbul unui profit
          </li>
          <li>
            Aplicatia Marketplace Echipamente Muzicale perimite vinderea
            exclusiva a echipamentelor muzicale fiind interzisa postarea
            oricarui produs din alt domneiu.
          </li>
          <li>
            Dati fiecarui produs o descriere cat mai completa si mai precisa.
            Asigurati-va ca specificati posibilele defecte cum ar fi zgarieturi,
            lovituri sau orice alte urme de folosinta. Mentionati orice
            componenta care lipseste pentru ca potentialul cumparator sa stie la
            ce sa se astepte odata cu primirea produsului (exemplu: manual de
            utilizare, cabluri, accesorii, etc.)
          </li>
          <li>
            Includeti imagini detaliate a produsului pe care il vindeti. Acest
            lucuru va va ajuta sa vindeti mai repede,iar articolul va lua o
            pozitie mai proeminienta pe platforma Marketplace Echipamente
            Muzicale.
          </li>
          <li>
            Asigurati-ca va dumneavoastra impreuna cu cumparatorul sunteti clari
            in privinta pretului impus. Specificati cine va suporta cheltuielile
            de livrare.
          </li>
          <li>
            La trimitere, impachetati produsul corespunzator oferindu-i
            siguranta maxima si asigurati coletul in caz de pierdere sau de
            deteriorare.
          </li>
          <li>
            Atat vanzatorul cat si cumparatorul trebuie sa stabilieasca impreuna
            modalitatea de plata. Este indicata utilizarea metodei plata la
            livrare cu verificare colet, insa sunt si alte metode posibile.
          </li>
          <li>
            Confirmati tranzactia inainte de livrare. Asigurati-va ca primiti
            detaliile necesare pentru a efectua plata si livrarea.
          </li>
          <li>
            Pe parcursul tranzactiei, confirmati cumparatorului statusul
            livrarii pe cat posibil.
          </li>
          <li>
            Pastrati legatura cu cumparatorul cat mai mult, asigurandu-va ca
            acesta primeste satisfacator produsul si la timp.
          </li>
        </ul>
        <p className="anunt-important">
          <strong>Atntie!</strong> Marketplace-ul Echipamente Muzicale nu este
          responsabil pentru anunturi postate, noi nu avem nici o parte in
          tranzactie. Noi suntem implicati doar in punerea la dispozitie a unei
          platforme publice pentru facilitarea tranzactiilor intre persoanele
          doritoare sa vanda/cumpere echipamente muzicale
        </p>
      </div>
    );
  }
}

export default TermeniSiConditii;
