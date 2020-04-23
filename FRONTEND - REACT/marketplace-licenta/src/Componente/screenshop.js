import React, { Component } from "react";
import Produs from "./Produs";
import "./ListaProduseCategorie.css";
import { Link } from "react-router-dom";

class ListaProduseCategorie extends Component {
  state = { listaSubcategorii: [], listaProduse: [] };

  getSubcategories = (props = this.props) => {
    //Extragere subcategorii din baza de date și plasare în state
  };

  getProduse = (props = this.props) => {
    fetch(
      `http://localhost:8888/produs/findProduseByCategorie/${
        props.match.params.numeCategorie
      }`
    )
      .then(res => res.json())
      .then(res => this.setState({ listaProduse: res }))
      .catch(e => console.log(e));
  };

  componentDidMount() {
    this.getSubcategories();
    this.getProduse();
  }

  componentWillReceiveProps(newProps) {
    if (
      newProps.match.params.numeCategorie !==
      this.props.match.params.numeCategorie
    ) {
      this.getSubcategories(newProps);
      this.getProduse(newProps);
    }
  }

  render() {
    const header = (
      <div>
        <p className="header">{this.props.match.params.numeCategorie}</p>
      </div>
    );

    const subcategorii = this.state.listaSubcategorii.map(subcategorie => (
      <Link
        to={`/subcategorieProduse/${this.props.match.params.numeCategorie}/${
          subcategorie.numeSubcategorie
        }`}
        key={subcategorie.idSubcategorie}
        className="subcategorie"
      >
        {subcategorie.numeSubcategorie} <br />
      </Link>
    ));

    const produse = this.state.listaProduse.map(produs => (
      <Produs key={produs.idProdus} {...produs} />
    ));

    return (
      <div className="lista-produse">
        <div className="col-left">
          {header}
          <div className="list">{subcategorii}</div>
        </div>
        <div className="col-right">{produse}</div>
      </div>
    );
  }
}

export default ListaProduseCategorie;
