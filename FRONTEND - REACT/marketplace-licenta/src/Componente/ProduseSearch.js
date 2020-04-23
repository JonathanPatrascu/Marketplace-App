import React, { Component } from "react";
import "./ProduseSearch.css";
import Produs from "./Produs";
import { Link } from "react-router-dom";

class ProduseSearch extends Component {
  state = { textIntrodus: "", listaProduse: [], listaCategorii: [] };

  getProduse = (props = this.props) => {
    fetch(
      `http://localhost:8888/produs/findProduseByWord/${
        props.match.params.textCautare
      }`
    )
      .then(res => res.json())
      .then(res => this.setState({ listaProduse: res }))
      .catch(e => console.log(e));
  };

  geCategories = (props = this.props) => {
    fetch(`http://localhost:8888/categorie/findAll`)
      .then(res => res.json())
      .then(res => this.setState({ listaCategorii: res }))
      .catch(e => console.log(e));
  };

  componentDidMount() {
    this.getProduse();
    this.geCategories();
  }

  componentWillReceiveProps(newProps) {
    if (
      newProps.match.params.textCautare !== this.props.match.params.textCautare
    ) {
      this.getProduse(newProps);
      this.geCategories(newProps);
    }
  }

  render() {
    const produse = this.state.listaProduse.map(produs => (
      <Produs key={produs.idProdus} {...produs} />
    ));
    console.log("produse", this.state.listaProduse);

    const categorii = this.state.listaCategorii.map(categorie => (
      <Link
        to={`/categorieProduse/${categorie.numeCategorie}`}
        className="categoriee"
      >
        {categorie.numeCategorie} <br />
      </Link>
    ));

    return (
      <div className="lista-produse">
        <div className="col-left">
          <h2 className="titlu-categorii">Categorii</h2>
          <div className="list">{categorii}</div>
        </div>

        <div className="col-right">
          <div className="descriere-cautare">
            Rezultatele cautarii pentru "{this.props.match.params.textCautare}"
          </div>
          <div>{produse}</div>
        </div>
      </div>
    );
  }
}

export default ProduseSearch;
