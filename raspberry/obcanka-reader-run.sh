#!/usr/bin/env bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
OUT=$1
if [[ -z "$OUT" ]] ; then OUT=/dev/ttyS0 ; fi

java -jar obcanka-reader-all.jar | while read J; do 
  tmp=`tempfile`
  echo $J | jq -r 'keys[] as $k | "\($k)=\"\(.[$k])\" "' > $tmp
  echo "cat <<END" >> $tmp
  if [[ -f /boot/obcanka-reader-template.txt ]] ; then 
    cat /boot/obcanka-reader-template.txt >> $tmp
  else
    cat obcanka-reader-template.txt >> $tmp
  fi
  echo "END" >> $tmp
  bash $tmp | iconv -f utf8 -t ascii//TRANSLIT > $OUT
  rm "$tmp"
done


