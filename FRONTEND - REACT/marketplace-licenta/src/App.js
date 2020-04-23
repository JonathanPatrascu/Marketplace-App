import React from "react";
import { Redirect } from "react-router-dom";
import "./App.css";
import Navigare from "./Componente/Navigare";
import Footer from "./Componente/Footer";
import ListaDorinte from "./Componente/ListaDorinte";
import PaginaStart from "./Componente/PaginaStart";
import ProdusDetaliat from "./Componente/ProdusDetaliat";
import { Switch, Route } from "react-router-dom";
import PageNotFound from "./Componente/PageNotFound";
import ContulMeu from "./Componente/ContulMeu";
import VindeProdus from "./Componente/VindeProdus";
import Informatii from "./Componente/Informatii";
import Contact from "./Componente/Contact";
import TermeniSiConditii from "./Componente/TermeniSiConditii";
import ListaProduseCategorie from "./Componente/ListaProduseCategorie";
import ListaProduseSubcategorie from "./Componente/ListaProduseSubcategorie";
import Login from "./Componente/Login";
import CreareCont from "./Componente/CreareCont";
import ProduseSearch from "./Componente/ProduseSearch";
import EditareInformatii from "./Componente/EditareInformatii";
import EditareProdus from "./Componente/EditareProdus";

function ProtectAuth(Component) {
  return function WrappedComponent() {
    const isConnected = localStorage.getItem("token");
    if (isConnected) {
      const user = JSON.parse(localStorage.getItem("user"));
      return <Component token={isConnected} user={user} />;
    } else {
      return <Redirect to="/login" />;
    }
  };
}

function App() {
  return (
    <>
      <Navigare />
      <Switch>
        <Route exact path="/" component={PaginaStart} />
        <Route path="/listaDorinte" component={ProtectAuth(ListaDorinte)} />
        <Route path="/produsDetaliat/:idProdus" component={ProdusDetaliat} />
        <Route
          path="/editareProdus/:idProdus"
          component={ProtectAuth(EditareProdus)}
        />
        <Route
          path="/categorieProduse/:numeCategorie"
          component={ListaProduseCategorie}
        />
        <Route path="/vindeProdus" component={ProtectAuth(VindeProdus)} />
        <Route path="/login" component={Login} />
        <Route path="/informatii" component={Informatii} />
        <Route path="/creareCont" component={CreareCont} />
        <Route
          path="/editareInformatiiProfil"
          component={ProtectAuth(EditareInformatii)}
        />
        <Route path="/searchProduse/:textCautare" component={ProduseSearch} />
        <Route
          path="/subcategorieProduse/:numeCategorie/:numeSubcategorie"
          component={ListaProduseSubcategorie}
        />
        <Route path="/contact" component={Contact} />
        <Route path="/contulMeu" component={ProtectAuth(ContulMeu)} />
        <Route path="/termeniConditii" component={TermeniSiConditii} />
        <Route path="**" component={PageNotFound} />
      </Switch>
      <Footer />
    </>
  );
}
export default App;
