import React, { Component } from "react";
import "./VindeProdus.css";
import ImageUploader from "react-images-upload";
import base64 from "image-to-base64";

class VindeProdus extends Component {
  state = {
    numeProdus: "",
    pretProdus: "",
    transportGratuit: false,
    descriereProdus: "",
    dataPostare: null,
    categorie: null,
    subcategorie: { idSubcategorie: 1 },
    stareProdus: { idStareProdus: 1 },
    vanzator: null,
    imaginiProdus: [],
    brandProdus: "",
    loading: false
  };
  categorieChange = event => {
    console.log("VALUE", event.target.value);
  };

  titluChange = event => {
    this.setState({ numeProdus: event.target.value });
  };

  pretChange = event => {
    this.setState({ pretProdus: event.target.value });
  };

  transportChange = event => {
    console.log("NEW TRASNPORT", event.target.value);
    this.setState({ transportGratuit: event.target.value });
  };

  stareChange = event => {
    this.setState({ stareProdus: { idStareProdus: event.target.value } });
  };

  descriereChange = event => {
    this.setState({ descriereProdus: event.target.value });
  };

  brandChange = event => {
    this.setState({ brandProdus: event.target.value });
  };
  categorieChange = event => {
    this.setState({ subcategorie: { idSubcategorie: event.target.value } });
  };

  onDrop = imaginiProdus => {
    this.setState({
      imaginiProdus: imaginiProdus.map(imagine => URL.createObjectURL(imagine))
    });
  };

  uploadImages = async imagesArray =>
    Promise.all(
      imagesArray.map(image => {
        return fetch("https://api.imgur.com/3/image", {
          method: "POST",
          body: JSON.stringify({
            image
          }),
          headers: {
            "Content-Type": "application/json",
            Authorization: `Client-ID f95514a5d23f091`
          }
        })
          .then(res => res.json())
          .then(res => res.data.link);
      })
    );

  saveProdus = async event => {
    const { imaginiProdus } = this.state;
    const { token } = this.props;
    const { loading, ...user } = this.state;

    event.preventDefault();
    this.setState({ loading: true });

    const base64Array = await Promise.all(
      imaginiProdus.map(url => base64(url))
    );
    const imagePaths = await this.uploadImages(base64Array);

    const options = {
      method: "POST",
      body: JSON.stringify({
        ...user,
        imaginiProdus: imagePaths.join(",")
      }),
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    };

    await fetch("http://localhost:8888/produs/saveProdus", options);

    this.setState({ loading: false });
  };

  verificareLungime = object => {
    if (object.target.value.length > object.target.maxLength)
      object.target.value = object.target.value.slice(
        0,
        object.target.maxLength
      );
  };

  render() {
    const {
      titluValue,
      pretValue,
      brandValue,
      transportGratuit,
      descriereValue,
      loading,
      imaginiProdus
    } = this.state;

    return (
      <div className="vinde">
        <h1 className="heading-vinde-produs">Vinde un produs</h1>
        <form
          action="postare-produs"
          className="form-postare-produs"
          onSubmit={this.saveProdus}
        >
          <div className="label-titlu">Titlu anunt</div>

          <input
            value={titluValue}
            onChange={this.titluChange}
            type="text"
            name="titlu"
            required
            maxLength="50"
            onInput={this.verificareLungime}
          />
          <br />
          <div className="label-pret">Pret RON</div>
          <div className="label-brand">Brand</div>

          <br />

          <input
            value={pretValue}
            onChange={this.pretChange}
            type="number"
            name="pret"
            required
            className="input-pret"
          />
          <input
            value={brandValue}
            onChange={this.brandChange}
            type="text"
            name="brand"
            required
            className="input-brand"
            maxLength="15"
            onInput={this.verificareLungime}
          />
          <br />

          <div className="label-transport">Transport</div>
          <div className="label-stare">Stare</div>

          <br />
          <select
            onChange={this.transportChange}
            value={transportGratuit}
            name="transport"
            id="1"
            className="select-transport"
          >
            <option value="true">Inclus</option>
            <option value="false">Neinclus</option>
          </select>

          <select
            onChange={this.stareChange}
            name="stare"
            id="2"
            className="select-stare"
          >
            <option value="1">Nou</option>
            <option value="2">Excelent</option>
            <option value="3">Foarte bun</option>
            <option value="4">Bun</option>
            <option value="5">Acceptabil</option>
            <option value="6">Deteriorat</option>
          </select>

          <div className="label-descriere">Descriere Produs</div>
          <div className="text-descriere-produs">
            <textarea
              value={descriereValue}
              onChange={this.descriereChange}
              form="postare-produs"
              name=""
              id=""
              cols="123"
              rows="10"
            />
          </div>
          <div className="label-categorie">Categorie produs</div>

          <select
            onChange={this.categorieChange}
            name="categorie"
            id="3"
            className="select-categorie"
            onChange={this.categorieChange}
          >
            <optgroup label="Studio & Inregistrari">
              <option value="1">Mixere Studio</option>
              <option value="2">Casti Studio</option>
              <option value="3">Monitoare Studio</option>
              <option value="4">Preamp-uri</option>
              <option value="5">Procesoare de Semnal</option>
              <option value="6">Interfete Audio</option>
              <option value="7">Computere</option>
              <option value="8">Software & Plugin-uri</option>
              <option value="9">iOS/iPad</option>
              <option value="10">Recordere</option>
              <option value="11">Echipamente Video</option>
            </optgroup>
            <optgroup label="Chitare">
              <option value="12">Chitare Electrice</option>
              <option value="13">Chitare Acustice</option>
              <option value="14">Ukulele</option>
              <option value="15">Amplificatoare Chitara</option>
              <option value="16">Pedale & Efecte</option>
              <option value="17">Sisteme Wireless</option>
              <option value="18">Accesorii Chitara</option>
            </optgroup>

            <optgroup label="Bas">
              <option value="19">Chitare Bas</option>
              <option value="20">Amplificatoare Bas</option>
              <option value="21">Pedale si Efecte Bas</option>
              <option value="22">Sisteme Wireless Bas</option>
              <option value="23">Accesorii Bas</option>
            </optgroup>

            <optgroup label="Clape & Sintetizatoare">
              <option value="24">Claviaturi</option>
              <option value="25">Sintetizatoare</option>
              <option value="26">Claviaturi MIDI</option>
              <option value="27">Piane</option>
              <option value="28">Pianine</option>
              <option value="29">Orgi Digitale</option>
              <option value="30">Orgi Clasice </option>

              <option value="31">Acordeoane</option>
              <option value="32">Accesorii pentru Pian</option>
            </optgroup>

            <optgroup label="Tobe & Percutie">
              <option value="33">Tobe acustice</option>
              <option value="34">Tobe electrice</option>
              <option value="35">Stative</option>
              <option value="36">Cinele</option>
              <option value="37">Consumabile</option>
              <option value="38">Percutie & Orchestra</option>
              <option value="39">Accesorii Tobe</option>
            </optgroup>

            <optgroup label="Microfoane & Wireless">
              <option value="40">Microfoane Condenser</option>
              <option value="41">Microfoane Dinamice</option>
              <option value="42">Microfoane Wireless</option>
              <option value="43">Microfoane Ribbon</option>
              <option value="44"> Microfoane de tobe</option>
              <option value="45">Microfoane Lavaliera</option>
              <option value="46">Microfoane Headset</option>
              <option value="47">Microfoane Shotgun</option>
              <option value="48">Microfoane USB</option>
              <option value="49">Accesorii Microfoane</option>
            </optgroup>

            <optgroup label="Sunet & Lumini">
              <option value="50">Mixere Live</option>
              <option value="51">Sisteme PA</option>
              <option value="52">Sisteme Monitorizare Live</option>
              <option value="53">Amplificatoare de Putere</option>
              <option value="54">Boxe si Speakere</option>
              <option value="55">Lumini</option>
              <option value="56">Componente Scena</option>
              <option value="57">Rack-uri si Case-uri</option>
              <option value="58">Accesorii sunet Live</option>
            </optgroup>

            <optgroup label="DJ & Electronice">
              <option value="59">Controlere DJ</option>
              <option value="60">Mixere DJ</option>
              <option value="61">Playere Media DJ</option>
              <option value="62">Turntables</option>
              <option value="63">Controlere MIDI DJ</option>
              <option value="64">Software DJ</option>
              <option value="65">Casti DJ</option>
              <option value="66">Dispozitive creeat Beat-uri</option>
              <option value="67">Efecte DJ</option>
              <option value="68">Accesorii DJ</option>
            </optgroup>

            <optgroup label="Altele">
              <option value="69">Instrumente Alama</option>
              <option value="70">Intrumente Traditionale</option>
              <option value="71">Cutii si Case-uri</option>
              <option value="72">Transport</option>
              <option value="73">Carti Muzica</option>
              <option value="74">Stative</option>
              <option value="75">Cadouri</option>
              <option value="76">Decoratiuni si Colectie</option>
              <option value="77">Produse Intretinere</option>
              <option value="78">Alte Accesorii Muzica</option>
            </optgroup>
          </select>
          <div className="imagini">
            {imaginiProdus.map(imagine => (
              <div
                mama={imagine}
                key={imagine}
                style={{ backgroundImage: `url(${imagine})` }}
              />
            ))}
          </div>

          <div className="imagine-label-vinde">Incarca imagini</div>
          <ImageUploader
            withIcon={true}
            buttonText="Choose images"
            onChange={this.onDrop}
            imgExtension={[".jpg", ".gif", ".png", ".gif"]}
            maxFileSize={5242880}
          />

          <div className="div-salvare-produs">
            <button type="submit" className="buton-salveaza-produs">
              {loading ? "Produsul se posteaza..." : "Posteaza produs"}
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default VindeProdus;
