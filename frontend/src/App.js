import './App.css';
import CurrencyInput from "./CurrencyInput";
import ConversionHistory from "./ConversionHistory";
import {useState, useEffect} from "react";
import axios from "axios";

function App() {

    const [amount1, setAmount1] = useState(1);
    const [amount2, setAmount2] = useState(1);
    const [currency1, setCurrency1] = useState('USD');
    const [currency2, setCurrency2] = useState('USD');
    const [rates, setRates] = useState([]);
    const [history, setHistory] = useState([]);

    useEffect(() => {
        axios.get("/myapi/getCurrencyRates")
            .then(response => {
                setRates(response.data.rates);
            })
    }, []);

    useEffect(() => {
        if(!!rates) {
            handleAmount1Change(1);
        }
    }, [rates]);


    useEffect(() => {
        axios.get("/myapi/getConversionHistoryList")
            .then(response => {
                setHistory(response.data);
            })
    }, []);

    function handleHistory() {
        axios.get("/myapi/getConversionHistoryList")
        .then(response => {
            setHistory(response.data);
        });
    }

    function format(number) {
        return number.toFixed(4);
    }

    function saveConversion(convStr) {
        axios.post("/myapi/saveConversionInHistory", null, { params: {
            conversion: convStr
        }})
        .then(response => {
            console.log(response);
        });
    }

    function formatConversionHistoryStr(amount1, currency1, amount2, currency2) {
        return amount1 + " " + currency1 + " - " + amount2 + " " + currency2;
    }

    function handleAmount1Change(amount1) {
        var amount2updated = format(amount1 * rates[currency2] / rates[currency1]);
        setAmount2(amount2updated);
        setAmount1(amount1);
        saveConversion(formatConversionHistoryStr(amount1, currency1, amount2updated, currency2));
        handleHistory();
    }

    function handleCurrency1Change(currency1) {
        var amount2updated = format(amount1 * rates[currency2] / rates[currency1]);
        setAmount2(amount2updated);
        setCurrency1(currency1);
        saveConversion(formatConversionHistoryStr(amount1, currency1, amount2updated, currency2));
        handleHistory();
    }

    function handleAmount2Change(amount2) {
        var amount1updated = format(amount2 * rates[currency1] / rates[currency2]);
        setAmount1(amount1updated);
        setAmount2(amount2);
        saveConversion(formatConversionHistoryStr(amount1updated, currency1, amount2, currency2));
        handleHistory();
    }

    function handleCurrency2Change(currency2) {
        var amount1updated = format(amount2 * rates[currency1] / rates[currency2]);
        setAmount1(amount1updated);
        setCurrency2(currency2);
        saveConversion(formatConversionHistoryStr(amount1updated, currency1, amount2, currency2));
        handleHistory();
    }

	return (
		<div>
		    <h1>Currency Convertor</h1>

		    <CurrencyInput
		     onAmountChange={handleAmount1Change}
		     onCurrencyChange={handleCurrency1Change}
		     currencies={Object.keys(rates)}
		     amount={amount1}
		     currency={currency1} />

		    <CurrencyInput
		     onAmountChange={handleAmount2Change}
		     onCurrencyChange={handleCurrency2Change}
		     currencies={Object.keys(rates)}
		     amount={amount2}
		     currency={currency2} />

		    <h3>Conversion History</h3>

            <ConversionHistory
             history={Object.values(history)} />
		</div>

	);

}

export default App;
