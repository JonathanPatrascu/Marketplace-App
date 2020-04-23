import React, { Component } from "react";
import Produs from "./Produs";
import { Link } from "react-router-dom";
import "./ListaProduseSubcategorie.css";

class ListaProduseSubcategorie extends Component {
  state = { listaSubcategorii: [], listaProduse: [] };

  getSubcategories = async (props = this.props) => {
    fetch(
      `http://localhost:8888/categorie/subcategoriiInCategorie/${
        props.match.params.numeCategorie
      }`
    )
      .then(res => res.json())
      .then(res => this.setState({ listaSubcategorii: res }))
      .catch(e => console.log(e));
  };

  getProduse = async (props = this.props) => {
    fetch(
      `http://localhost:8888/produs/findProduseBySubcategorie/${
        props.match.params.numeSubcategorie
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
      newProps.match.params.numeSubcategorie !==
      this.props.match.params.numeSubcategorie
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
        className="subcategorie"
      >
        {subcategorie.numeSubcategorie} <br />
      </Link>
    ));

    const produse = this.state.listaProduse.map(produs => (
      <Produs key={produs.idProdus} {...produs} />
    ));
    console.log("produs:", this.state.listaProduse);

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

export default ListaProduseSubcategorie;
