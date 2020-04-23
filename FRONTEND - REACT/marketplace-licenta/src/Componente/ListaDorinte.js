import React, { Component } from "react";
import "./ListaDorinte.css";
import ProdusDorit from "./ProdusDorit";

class ListaDorinte extends Component {
  state = { produseDorite: [] };

  getProduseLista = (props = this.props) => {
    const { token } = props;

    fetch(
      `http://localhost:8888/listaDorinte/findListaDorinteById/${
        props.user.idUtilizator
      }`,
      {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    )
      .then(res => res.json())
      .then(res => this.setState({ produseDorite: res.listaProduseDorite }))
      .catch(e => console.log(e));
  };

  golesteListaDorinte = () => {
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8888/listaDorinte/clearAllListaDorinte`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    })
      .then(res => res.json())
      .then(() => this.setState({ produseDorite: [] }))
      .catch(e => console.log(e));
  };

  componentDidMount() {
    this.getProduseLista();
  }
  stergeProdus = idProdus => {
    const { produseDorite } = this.state;
    this.setState({
      produseDorite: produseDorite.filter(
        produseDorite => produseDorite.idProdus !== idProdus
      )
    });
  };
  render() {
    const produse = this.state.produseDorite.map(produs => (
      <div key={produs.idProdus}>
        <ProdusDorit {...produs} stergeProdus={this.stergeProdus} />
        <br />
      </div>
    ));

    return (
      <div className="container-mare">
        <div className="titlu-lista">Lista dorinte</div>
        {this.state.produseDorite.length > 0 ? (
          <div>
            <div className="container-elemente-lista-dorinte">{produse}</div>
            <div className="div-salvare-produs">
              <button
                onClick={this.golesteListaDorinte}
                className="buton-goleste-lista"
              >
                Goleste lista
              </button>
            </div>
          </div>
        ) : (
          <div className="mesaj-lista-goala">
            Nu exista niciun produs in lista de dorinte
          </div>
        )}
      </div>
    );
  }
}

export default ListaDorinte;
