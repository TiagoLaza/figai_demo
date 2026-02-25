import React, { useEffect, useState } from "react";

interface IngredientDetails {
    [key: string]: string;
}

interface Ingredient {
    id: number;
    item_id: string;
    listing: string;
    category: string;
    details: IngredientDetails;
    certifications: string;
    sourcing: string;
    pricing: string;
    availability: string;
    technical: string;
    suggested_use: string;
    notes: string;
}

interface PageResponse {
    content: Ingredient[];
    totalPages: number;
    number: number;
    last: boolean;
}

const PAGE_SIZE = 5;

export default function IngredientList() {
    const [data, setData] = useState<Ingredient[]>([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchIngredients(0);
    }, []);

    const fetchIngredients = async (pageNumber: number) => {
        setLoading(true);
        try {
            const response = await fetch(
                `/api/ingredient/list?page=${pageNumber}&size=${PAGE_SIZE}`
            );
            const json: PageResponse = await response.json();

            if (pageNumber === 0) {
                setData(json.content);
            } else {
                setData((prev) => [...prev, ...json.content]);
            }

            setPage(json.number);
            setTotalPages(json.totalPages);
        } catch (error) {
            console.error("Error fetching ingredients:", error);
        } finally {
            setLoading(false);
        }
    };

    const loadMore = () => {
        if (page + 1 < totalPages) {
            fetchIngredients(page + 1);
        }
    };

    return (
        <div style={{ padding: "20px", overflowX: "auto" }}>
            <h1>Ingredient List</h1>

            <table style={{ borderCollapse: "collapse", width: "100%" }}>
                <thead>
                <tr>
                    {[
                        "Item ID",
                        "Listing",
                        "Category",
                        "Details",
                        "Certifications",
                        "Sourcing",
                        "Pricing",
                        "Availability",
                        "Technical",
                        "Suggested Use",
                        "Notes",
                    ].map((col) => (
                        <th key={col} style={thStyle}>
                            {col}
                        </th>
                    ))}
                </tr>
                </thead>
                <tbody>
                {data.map((item) => (
                    <tr key={item.id}>
                        <td style={tdStyle}>{item.item_id}</td>
                        <td style={tdStyle}>{item.listing}</td>
                        <td style={tdStyle}>{item.category}</td>
                        {/* Details hover card */}
                        <td style={tdStyle}>
                            <div style={{ position: "relative", display: "inline-block" }}>
                                <button style={detailsButtonStyle}>View</button>
                                <div style={hoverCardStyle}>
                                    {Object.entries(item.details).map(([key, value]) => (
                                        <div key={key}>
                                            <strong>{key}:</strong> {value}
                                        </div>
                                    ))}
                                </div>
                            </div>
                        </td>
                        <td style={tdStyle}>{item.certifications}</td>
                        <td style={tdStyle}>{item.sourcing}</td>
                        <td style={tdStyle}>{item.pricing}</td>
                        <td style={tdStyle}>{item.availability}</td>
                        <td style={tdStyle}>{item.technical}</td>
                        <td style={tdStyle}>{item.suggested_use}</td>
                        <td style={tdStyle}>{item.notes}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            {loading && <p>Loading...</p>}

            {page + 1 < totalPages && (
                <button
                    onClick={loadMore}
                    style={{
                        padding: "10px 20px",
                        borderRadius: "6px",
                        background: "#007bff",
                        color: "#fff",
                        border: "none",
                        cursor: "pointer",
                        marginTop: "12px",
                    }}
                >
                    {loading ? "Loading..." : "Load More"}
                </button>
            )}
        </div>
    );
}

// ----------------- Styles -----------------
const thStyle: React.CSSProperties = {
    border: "1px solid #ccc",
    padding: "8px",
    background: "#f0f0f0",
    textAlign: "left",
};

const tdStyle: React.CSSProperties = {
    border: "1px solid #ccc",
    padding: "8px",
    verticalAlign: "top",
};

const detailsButtonStyle: React.CSSProperties = {
    padding: "4px 8px",
    cursor: "pointer",
    borderRadius: "4px",
    border: "1px solid #007bff",
    backgroundColor: "#fff",
    color: "#007bff",
    fontWeight: "bold",
};

const hoverCardStyle: React.CSSProperties = {
    display: "none",
    position: "absolute",
    top: "100%",
    left: 0,
    backgroundColor: "#fff",
    border: "1px solid #ccc",
    borderRadius: "6px",
    padding: "8px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.15)",
    zIndex: 10,
    width: "200px",
};

// Show hover card on button hover
document.addEventListener("mouseover", (e) => {
    const target = e.target as HTMLElement;
    if (target.tagName === "BUTTON" && target.textContent === "View") {
        const card = target.nextElementSibling as HTMLElement;
        if (card) card.style.display = "block";
    }
});

document.addEventListener("mouseout", (e) => {
    const target = e.target as HTMLElement;
    if (target.tagName === "BUTTON" && target.textContent === "View") {
        const card = target.nextElementSibling as HTMLElement;
        if (card) card.style.display = "none";
    }
});