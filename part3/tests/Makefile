filename=tests

output:
	lualatex --output-format=pdf --file-line-error --interaction=nonstopmode -shell-escape $(filename).tex

pdf: pdf
	lualatex --output-format=pdf --file-line-error --interaction=nonstopmode -shell-escape $(filename).tex

pdf-print: ps
	ps2pdf -dColorConversionStrategy=/LeaveColorUnchanged -dPDFSETTINGS=/printer ${filename}.ps

text: html
	html2text -width 100 -style pretty ${filename}/${filename}.html | sed -n '/./,$$p' | head -n-2 >${filename}.txt

html:
	@#latex2html -split +0 -info "" -no_navigation ${filename}
	htlatex ${filename}

ps:	dvi
	dvips -t letter ${filename}.dvi

dvi:
	lualatex --output-format=dvi $(filename).tex

view:
	 zathura ${filename}.pdf & 2>/dev/null || xdg-open ${filename}.pdf & 2>/dev/null || open ${filename}.pdf

remove:
	rm -rf ${filename}.{ps,log,aux,out,dvi,bbl,blg,fdb_latexmk,fls,synctex.gz,pyg} _minted-${filename} *.log 

clean:
	rm -rf ${filename}.{ps,pdf,log,aux,out,dvi,bbl,blg,fdb_latexmk,fls,synctex.gz,pyg} _minted-${filename} *.log 
